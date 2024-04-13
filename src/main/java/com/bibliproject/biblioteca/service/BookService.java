package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.BookDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.repository.BookRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto createBook(BookDto bookDTO) {
        Book book = BookMapper.INSTANCE.convertDtoToEntity(bookDTO);
        System.out.println("created");
        bookRepository.save(book);
        return BookMapper.INSTANCE.convertEntityToDto(book);
    }

    public BookDto updateBook(BookDto bookDTO) {
        return null;
    }

    public  BookDto deleteBook(BookDto bookDTO) {
        return null;
    }
}
