package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT l FROM Book l WHERE l.isDeleted = false")
    Page<Book> findAllNotDeleted(Pageable pageable);

    @Query("SELECT l FROM Book l WHERE l.isDeleted = true")
    Page<Book> findAllDeleted(Pageable pageable);

    @Query("SELECT l FROM Book l WHERE l.id = :id AND l.isDeleted = false")
    Optional<Book> findByIdAndNotDeleted(@Param("id") Long id);
}
