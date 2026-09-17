package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import org.springframework.stereotype.Component;

@Component
public class DeleteLoanUseCase {

    private final LoanRepository loanRepository;

    public DeleteLoanUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(Long id) {
        Loan loan = loanRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new LoanNotFoundException("Empréstimo com o id: " + id + " não foi encontrado."));

        if (loan.getReturnDate() == null) {
            throw new StudentHaveDebtException(
                    "O livro ainda não foi retornado, retorne-o para que o registro possa ser deletado.");
        }

        loan.markDeleted();
        loanRepository.save(loan);
    }
}
