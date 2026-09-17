package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListDeletedBooksUseCase {

    private final BookRepository bookRepository;

    public ListDeletedBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> execute(String search, Pageable pageable) {
        if (search != null) {
            return bookRepository.findAllDeletedAndMatchesSearch(search, pageable);
        }
        return bookRepository.findAllDeleted(pageable);
    }
}
