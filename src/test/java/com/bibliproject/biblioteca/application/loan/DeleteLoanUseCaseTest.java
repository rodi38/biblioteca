package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Test
    void marksTheLoanAsDeletedWhenAlreadyReturned() {
        Loan loan = new Loan();
        loan.setReturnDate(LocalDateTime.now());
        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(loan));

        new DeleteLoanUseCase(loanRepository).execute(1L);

        assertThat(loan.isDeleted()).isTrue();
        verify(loanRepository).save(loan);
    }

    @Test
    void throwsWhenLoanNotFound() {
        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new DeleteLoanUseCase(loanRepository).execute(1L))
                .isInstanceOf(LoanNotFoundException.class);
    }

    @Test
    void throwsWhenBookNotYetReturned() {
        Loan loan = new Loan();
        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(loan));

        assertThatThrownBy(() -> new DeleteLoanUseCase(loanRepository).execute(1L))
                .isInstanceOf(StudentHaveDebtException.class);
    }
}
