package com.bibliproject.biblioteca.infrastructure.persistence.loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanJpaRepository extends JpaRepository<LoanJpaEntity, Long> {

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = false")
    Page<LoanJpaEntity> findAllNotDeleted(Pageable pageable);

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = false AND (l.student.fullName LIKE %:search% OR l.student.email LIKE %:search% OR l.book.title LIKE %:search% OR l.book.isbn LIKE %:search%)")
    Page<LoanJpaEntity> findAllNotDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = true")
    Page<LoanJpaEntity> findAllDeleted(Pageable pageable);

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = true AND (l.student.fullName LIKE %:search% OR l.student.email LIKE %:search%" +
            " OR l.book.title LIKE %:search% OR l.book.isbn LIKE %:search%)")
    Page<LoanJpaEntity> findAllDeletedAndMatchesSearch(@Param("search") String search, Pageable pageable);

    @Query("SELECT l FROM Loan l WHERE l.id = :id AND l.isDeleted = false")
    Optional<LoanJpaEntity> findByIdAndNotDeleted(@Param("id") Long id);

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = false AND l.student.id = :studentId")
    Page<LoanJpaEntity> findAllNotDeletedByStudentId(@Param("studentId") Long studentId, Pageable pageable);

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = false AND l.returnDate IS NULL AND l.book.id = :bookId")
    List<LoanJpaEntity> findActiveByBookId(@Param("bookId") Long bookId);

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = false AND l.returnDate IS NULL AND l.student.id = :studentId")
    List<LoanJpaEntity> findActiveByStudentId(@Param("studentId") Long studentId);
}
