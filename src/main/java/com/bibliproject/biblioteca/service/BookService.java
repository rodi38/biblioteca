package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.repository.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDto> findAll() {
        List<Book> books = bookRepository.findAll();
        return BookMapper.toDtoList(books);
    }

    public BookResponseDto createBook(BookRequestDto bookRequestDto) {
        System.out.println("t1");
        if (bookRequestDto == null){
            throw new NullPointerException("livro nulo.");
        }
        Book book = BookMapper.dtoRequestToEntity(bookRequestDto);
        System.out.println("t2");

        System.out.println(bookRequestDto);
        bookRepository.saveAndFlush(book);
        return BookMapper.toDto(book);
    }



    public BookResponseDto updateBook(Long id, BookRequestDto bookRequestDto) {
        Book book = BookMapper.toEntityWithoutLoans(findById(id));
        BookMapper.bookUpdate(book, bookRequestDto);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDto(savedBook);
    }


    public boolean deleteBook(long id) {
        bookRepository.deleteById(id);
        return true;
    }

    public BookResponseDto findById(Long id) {
        return BookMapper.toDto(bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id)));
    }


}
