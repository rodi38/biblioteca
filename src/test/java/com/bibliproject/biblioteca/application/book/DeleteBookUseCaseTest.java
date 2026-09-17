package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.exception.book.BookCurrentlyLoanedException;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteBookUseCaseTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private LoanRepository loanRepository;

    @Test
    void marksTheBookAsDeletedWhenNotLoaned() {
        Book book = new Book();
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(book));
        when(loanRepository.findActiveByBookId(1L)).thenReturn(List.of());

        new DeleteBookUseCase(bookRepository, loanRepository).execute(1L);

        assertThat(book.isDeleted()).isTrue();
        verify(bookRepository).save(book);
    }

    @Test
    void throwsWhenBookNotFound() {
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new DeleteBookUseCase(bookRepository, loanRepository).execute(1L))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void throwsWhenBookIsCurrentlyLoaned() {
        Book book = new Book();
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(book));
        when(loanRepository.findActiveByBookId(1L)).thenReturn(List.of(new Loan()));

        assertThatThrownBy(() -> new DeleteBookUseCase(bookRepository, loanRepository).execute(1L))
                .isInstanceOf(BookCurrentlyLoanedException.class);
    }
}
