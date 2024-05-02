package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT l FROM Book l WHERE l.isDeleted = false")
    List<Book> findAllNotDeleted();

    @Query("SELECT l FROM Book l WHERE l.isDeleted = true")
    List<Book> findAllDeleted();

    @Query("SELECT l FROM Book l WHERE l.id = :id AND l.isDeleted = false")
    Optional<Book> findByIdAndNotDeleted(@Param("id") Long id);
}
