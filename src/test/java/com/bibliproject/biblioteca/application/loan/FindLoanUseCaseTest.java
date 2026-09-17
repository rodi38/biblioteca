package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.auth.AuthenticatedPrincipal;
import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private CurrentUserProvider currentUserProvider;

    @Test
    void returnsTheLoanWhenFound() {
        Loan loan = new Loan();
        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(loan));
        when(currentUserProvider.getCurrentUser()).thenReturn(new AuthenticatedPrincipal(9L, "admin@a.com", Role.ADMIN, null));

        Loan result = new FindLoanUseCase(loanRepository, currentUserProvider).execute(1L);

        assertThat(result).isSameAs(loan);
    }

    @Test
    void throwsWhenNotFound() {
        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new FindLoanUseCase(loanRepository, currentUserProvider).execute(1L))
                .isInstanceOf(LoanNotFoundException.class);
    }
}
