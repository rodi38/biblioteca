package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.auth.AuthenticatedPrincipal;
import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.exception.book.BookAlreadyReturnedException;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReturnLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CurrentUserProvider currentUserProvider;

    @BeforeEach
    void setUp() {
        lenient().when(currentUserProvider.getCurrentUser())
                .thenReturn(new AuthenticatedPrincipal(9L, "admin@a.com", Role.ADMIN, null));
    }

    private ReturnLoanUseCase useCase() {
        return new ReturnLoanUseCase(loanRepository, bookRepository, studentRepository, currentUserProvider);
    }

    @Test
    void marksReturnedAndRestoresStockAndBorrowedCount() {
        Book book = new Book();
        book.setStockQuantity(1);
        Student student = new Student();
        student.setBorrowedBooksCount(1);
        Loan loan = new Loan();
        loan.setBook(book);
        loan.setStudent(student);

        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Loan result = useCase().execute(1L);

        assertThat(result.getReturnDate()).isNotNull();
        assertThat(book.getStockQuantity()).isEqualTo(2);
        assertThat(student.getBorrowedBooksCount()).isEqualTo(0);
    }

    @Test
    void throwsWhenLoanNotFound() {
        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase().execute(1L))
                .isInstanceOf(LoanNotFoundException.class);
    }

    @Test
    void throwsWhenAlreadyReturned() {
        Loan loan = new Loan();
        loan.setReturnDate(LocalDateTime.now());
        when(loanRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(loan));

        assertThatThrownBy(() -> useCase().execute(1L))
                .isInstanceOf(BookAlreadyReturnedException.class);
    }
}
