package com.bibliproject.biblioteca.infrastructure.persistence.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final BookJpaRepository bookJpaRepository;

    public BookRepositoryImpl(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public Page<Book> findAllNotDeleted(Pageable pageable) {
        return bookJpaRepository.findAllNotDeleted(pageable).map(BookPersistenceMapper::toDomain);
    }

    @Override
    public Page<Book> findAllNotDeletedAndMatchesSearch(String search, Pageable pageable) {
        return bookJpaRepository.findAllNotDeletedAndMatchesSearch(search, pageable).map(BookPersistenceMapper::toDomain);
    }

    @Override
    public Page<Book> findAllDeleted(Pageable pageable) {
        return bookJpaRepository.findAllDeleted(pageable).map(BookPersistenceMapper::toDomain);
    }

    @Override
    public Page<Book> findAllDeletedAndMatchesSearch(String search, Pageable pageable) {
        return bookJpaRepository.findAllDeletedAndMatchesSearch(search, pageable).map(BookPersistenceMapper::toDomain);
    }

    @Override
    public Optional<Book> findByIdAndNotDeleted(Long id) {
        return bookJpaRepository.findByIdAndNotDeleted(id).map(BookPersistenceMapper::toDomain);
    }

    @Override
    public Book save(Book book) {
        BookJpaEntity saved = bookJpaRepository.save(BookPersistenceMapper.toJpaEntity(book));
        return BookPersistenceMapper.toDomain(saved);
    }
}
