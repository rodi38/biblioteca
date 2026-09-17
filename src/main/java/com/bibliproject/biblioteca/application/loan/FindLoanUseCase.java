package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class FindLoanUseCase {

    private final LoanRepository loanRepository;

    public FindLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan execute(Long id) {
        return loanRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new LoanNotFoundException("Empréstimo com o id: " + id + " não foi encontrado."));
    }
}
