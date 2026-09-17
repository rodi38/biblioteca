package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.auth.AuthenticatedPrincipal;
import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.domain.user.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListLoansUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private CurrentUserProvider currentUserProvider;

    @Test
    void delegatesToSearchQueryWhenSearchIsGiven() {
        Pageable pageable = Pageable.unpaged();
        Page<Loan> page = new PageImpl<>(java.util.List.of());
        when(currentUserProvider.getCurrentUser()).thenReturn(new AuthenticatedPrincipal(9L, "admin@a.com", Role.ADMIN, null));
        when(loanRepository.findAllNotDeletedAndMatchesSearch("term", pageable)).thenReturn(page);

        Page<Loan> result = new ListLoansUseCase(loanRepository, currentUserProvider).execute("term", pageable);

        assertThat(result).isSameAs(page);
        verify(loanRepository).findAllNotDeletedAndMatchesSearch("term", pageable);
        verifyNoMoreInteractions(loanRepository);
    }

    @Test
    void delegatesToListAllWhenSearchIsNull() {
        Pageable pageable = Pageable.unpaged();
        Page<Loan> page = new PageImpl<>(java.util.List.of());
        when(currentUserProvider.getCurrentUser()).thenReturn(new AuthenticatedPrincipal(9L, "admin@a.com", Role.ADMIN, null));
        when(loanRepository.findAllNotDeleted(pageable)).thenReturn(page);

        Page<Loan> result = new ListLoansUseCase(loanRepository, currentUserProvider).execute(null, pageable);

        assertThat(result).isSameAs(page);
        verify(loanRepository).findAllNotDeleted(pageable);
        verifyNoMoreInteractions(loanRepository);
    }

    @Test
    void restrictsToOwnLoansWhenCurrentUserIsStudent() {
        Pageable pageable = Pageable.unpaged();
        Page<Loan> page = new PageImpl<>(java.util.List.of());
        when(currentUserProvider.getCurrentUser()).thenReturn(new AuthenticatedPrincipal(9L, "student@a.com", Role.STUDENT, 5L));
        when(loanRepository.findAllNotDeletedByStudentId(5L, pageable)).thenReturn(page);

        Page<Loan> result = new ListLoansUseCase(loanRepository, currentUserProvider).execute("term", pageable);

        assertThat(result).isSameAs(page);
        verify(loanRepository).findAllNotDeletedByStudentId(5L, pageable);
        verifyNoMoreInteractions(loanRepository);
    }
}
