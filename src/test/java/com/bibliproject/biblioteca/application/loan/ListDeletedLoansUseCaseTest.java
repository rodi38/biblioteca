package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
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
class ListDeletedLoansUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Test
    void delegatesToSearchQueryWhenSearchIsGiven() {
        Pageable pageable = Pageable.unpaged();
        Page<Loan> page = new PageImpl<>(java.util.List.of());
        when(loanRepository.findAllDeletedAndMatchesSearch("term", pageable)).thenReturn(page);

        Page<Loan> result = new ListDeletedLoansUseCase(loanRepository).execute("term", pageable);

        assertThat(result).isSameAs(page);
        verify(loanRepository).findAllDeletedAndMatchesSearch("term", pageable);
        verifyNoMoreInteractions(loanRepository);
    }

    @Test
    void delegatesToListAllWhenSearchIsNull() {
        Pageable pageable = Pageable.unpaged();
        Page<Loan> page = new PageImpl<>(java.util.List.of());
        when(loanRepository.findAllDeleted(pageable)).thenReturn(page);

        Page<Loan> result = new ListDeletedLoansUseCase(loanRepository).execute(null, pageable);

        assertThat(result).isSameAs(page);
        verify(loanRepository).findAllDeleted(pageable);
        verifyNoMoreInteractions(loanRepository);
    }
}
