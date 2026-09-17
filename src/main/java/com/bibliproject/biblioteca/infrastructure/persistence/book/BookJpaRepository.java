package com.bibliproject.biblioteca.infrastructure.persistence.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookJpaRepository extends JpaRepository<BookJpaEntity, Long> {

    @Query("SELECT b FROM Book b WHERE b.isDeleted = false")
    Page<BookJpaEntity> findAllNotDeleted(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isDeleted = false AND (b.title LIKE %:search% OR b.isbn LIKE %:search% OR b.author LIKE %:search% OR b.category LIKE %:search% OR b.publisher LIKE %:search%)")
    Page<BookJpaEntity> findAllNotDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isDeleted = true AND (b.title LIKE %:search% OR b.isbn LIKE %:search% OR b.author LIKE %:search% OR b.category LIKE %:search% OR b.publisher LIKE %:search%)")
    Page<BookJpaEntity> findAllDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.isDeleted = true")
    Page<BookJpaEntity> findAllDeleted(Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.id = :id AND b.isDeleted = false")
    Optional<BookJpaEntity> findByIdAndNotDeleted(@Param("id") Long id);
}
