package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListLoansUseCase {

    private final LoanRepository loanRepository;

    public ListLoansUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Page<Loan> execute(String search, Pageable pageable) {
        if (search != null) {
            return loanRepository.findAllNotDeletedAndMatchesSearch(search, pageable);
        }
        return loanRepository.findAllNotDeleted(pageable);
    }
}
