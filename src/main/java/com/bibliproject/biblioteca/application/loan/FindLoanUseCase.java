package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.exception.auth.ForbiddenOperationException;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FindLoanUseCase {

    private final LoanRepository loanRepository;
    private final CurrentUserProvider currentUserProvider;

    public FindLoanUseCase(LoanRepository loanRepository, CurrentUserProvider currentUserProvider) {
        this.loanRepository = loanRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public Loan execute(Long id) {
        Loan loan = loanRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new LoanNotFoundException("Empréstimo com o id: " + id + " não foi encontrado."));

        var currentUser = currentUserProvider.getCurrentUser();
        if (!currentUser.isAdmin() && !currentUser.ownsStudent(loan.getStudent().getId())) {
            throw new ForbiddenOperationException("Você só pode consultar os seus próprios empréstimos.");
        }

        return loan;
    }
}
