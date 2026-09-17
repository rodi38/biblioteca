package com.bibliproject.biblioteca.domain.loan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LoanRepository {

    Page<Loan> findAllNotDeleted(Pageable pageable);

    Page<Loan> findAllNotDeletedAndMatchesSearch(String search, Pageable pageable);

    Page<Loan> findAllDeleted(Pageable pageable);

    Page<Loan> findAllDeletedAndMatchesSearch(String search, Pageable pageable);

    Optional<Loan> findByIdAndNotDeleted(Long id);

    List<Loan> findActiveByBookId(Long bookId);

    List<Loan> findActiveByStudentId(Long studentId);

    Loan save(Loan loan);
}
