package com.bibliproject.biblioteca.repository;

import com.bibliproject.biblioteca.domain.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    List<Loan> findLoansByStudentId(Long id);
}
