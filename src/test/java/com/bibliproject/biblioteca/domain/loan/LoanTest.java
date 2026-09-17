package com.bibliproject.biblioteca.domain.loan;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LoanTest {

    @Test
    void isOverdueWhenPastLimitDateAndNotReturned() {
        Loan loan = new Loan();
        loan.setLimitDate(LocalDateTime.now().minusDays(1));

        assertThat(loan.isOverdue()).isTrue();
    }

    @Test
    void isNotOverdueWhenAlreadyReturned() {
        Loan loan = new Loan();
        loan.setLimitDate(LocalDateTime.now().minusDays(1));
        loan.setReturnDate(LocalDateTime.now());

        assertThat(loan.isOverdue()).isFalse();
    }

    @Test
    void isNotOverdueWhenBeforeLimitDate() {
        Loan loan = new Loan();
        loan.setLimitDate(LocalDateTime.now().plusDays(1));

        assertThat(loan.isOverdue()).isFalse();
    }

    @Test
    void markReturnedSetsReturnDate() {
        Loan loan = new Loan();

        loan.markReturned();

        assertThat(loan.getReturnDate()).isNotNull();
    }

    @Test
    void markDeletedFlagsLoanAndSetsDeletedAt() {
        Loan loan = new Loan();

        loan.markDeleted();

        assertThat(loan.isDeleted()).isTrue();
        assertThat(loan.getDeletedAt()).isNotNull();
    }
}
