package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.isDeleted = false")
    Page<Book> findAllNotDeleted(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isDeleted = false AND (b.title LIKE %:search% OR b.isbn LIKE %:search% OR b.author  LIKE %:search% OR b.category LIKE %:search% OR b.publisher LIKE %:search%)")
    Page<Book> findAllNotDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isDeleted = true AND (b.title LIKE %:search% OR b.isbn LIKE %:search% OR b.author LIKE %:search% OR b.category LIKE %:search% OR b.publisher LIKE %:search%)")
    Page<Book> findAllDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isDeleted = true")
    Page<Book> findAllDeleted(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.isDeleted = false")
    Optional<Book> findByIdAndNotDeleted(@Param("id") Long id);
}
