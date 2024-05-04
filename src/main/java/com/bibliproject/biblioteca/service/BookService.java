package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.BookRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.BookResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.exception.book.BookCurrentlyLoanedException;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import com.bibliproject.biblioteca.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookResponseDto > findAll(String search, Pageable pageable) {
        Page<Book> books;

        if (search != null) {
           books = bookRepository.findAllNotDeletedAndMatchesSearch(search, pageable);
        } else {
            books = bookRepository.findAllNotDeleted(pageable);
        }
        return books.map(BookMapper::toDtoResponse);
    }

    public BookResponseDto findById(Long id) {
        return BookMapper.toDtoResponse(bookRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
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
        book.setUpdatedAt(LocalDateTime.now());
        Book savedBook = bookRepository.save(book);
        return BookMapper.toDtoResponse(savedBook);
    }

    public void delete(long id) {
        Book book = bookRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        for (Loan loan : book.getLoans()) {
            if (loan.getReturnDate() == null) {
                throw new BookCurrentlyLoanedException("O livro está emprestado, não é possivel deletar.");
            }
        }
        book.setDeleted(true);
        book.setDeletedAt(LocalDateTime.now());
        bookRepository.save(book);

    }

}