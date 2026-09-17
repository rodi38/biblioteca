package com.bibliproject.biblioteca.domain.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BookRepository {

    Page<Book> findAllNotDeleted(Pageable pageable);

    Page<Book> findAllNotDeletedAndMatchesSearch(String search, Pageable pageable);

    Page<Book> findAllDeleted(Pageable pageable);

    Page<Book> findAllDeletedAndMatchesSearch(String search, Pageable pageable);

    Optional<Book> findByIdAndNotDeleted(Long id);

    Book save(Book book);
}
