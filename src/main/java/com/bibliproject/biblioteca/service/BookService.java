package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import com.bibliproject.biblioteca.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List < BookResponseDto > findAll() {
        List < Book > books = bookRepository.findAll();
        return BookMapper. toDtoList(books);
    }

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        if (bookRequestDto == null) {
            throw new NullPointerException("livro nulo.");
        }
        Book book = BookMapper.dtoRequestToEntity(bookRequestDto);

        bookRepository.saveAndFlush(book);
        return BookMapper.toDtoResponse(book);
    }

    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book book = BookMapper.toEntity(findById(id));
        BookMapper.bookUpdate(book, bookRequestDto);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDtoResponse(savedBook);
    }

    public boolean delete(long id) {
        Book book = BookMapper.toEntity(findById(id));
        book.setDeleted(true);
        book.setDeletedAt(LocalDateTime.now());

        bookRepository.save(book);
        return true;
    }

    public BookResponseDto findById(Long id) {
        return BookMapper.toDtoResponse(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

}