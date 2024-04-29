package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findLoansByStudentId(Long id);

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = false")
    List<Loan> findAllNotDeleted();

    @Query("SELECT l FROM Loan l WHERE l.isDeleted = true")
    List<Loan> findAllDeleted();

    @Query("SELECT l FROM Loan l WHERE l.id = :id AND l.isDeleted = false")
    Optional<Loan> findByIdAndNotDeleted(@Param("id") Long id);
}
