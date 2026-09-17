package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListLoansUseCase {

    private final LoanRepository loanRepository;
    private final CurrentUserProvider currentUserProvider;

    public ListLoansUseCase(LoanRepository loanRepository, CurrentUserProvider currentUserProvider) {
        this.loanRepository = loanRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public Page<Loan> execute(String search, Pageable pageable) {
        var currentUser = currentUserProvider.getCurrentUser();
        if (!currentUser.isAdmin()) {
            return loanRepository.findAllNotDeletedByStudentId(currentUser.studentId(), pageable);
        }
        if (search != null) {
            return loanRepository.findAllNotDeletedAndMatchesSearch(search, pageable);
        }
        return loanRepository.findAllNotDeleted(pageable);
    }
}
