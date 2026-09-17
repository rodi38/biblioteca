package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FindBookUseCase {

    private final BookRepository bookRepository;

    public FindBookUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book execute(Long id) {
        return bookRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
}
