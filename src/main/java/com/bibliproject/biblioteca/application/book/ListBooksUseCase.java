package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListBooksUseCase {

    private final BookRepository bookRepository;

    public ListBooksUseCase(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> execute(String search, Pageable pageable) {
        if (search != null) {
            return bookRepository.findAllNotDeletedAndMatchesSearch(search, pageable);
        }
        return bookRepository.findAllNotDeleted(pageable);
    }
}
