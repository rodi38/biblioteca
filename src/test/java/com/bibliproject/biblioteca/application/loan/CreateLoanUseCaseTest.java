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
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import com.bibliproject.biblioteca.exception.loan.LoanOverdueException;
import com.bibliproject.biblioteca.exception.student.StudentBorrowLimitReachedException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateLoanUseCaseTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CurrentUserProvider currentUserProvider;

    @BeforeEach
    void setUp() {
        lenient().when(currentUserProvider.getCurrentUser())
                .thenReturn(new AuthenticatedPrincipal(9L, "admin@a.com", Role.ADMIN, null));
    }

    private CreateLoanUseCase useCase() {
        return new CreateLoanUseCase(loanRepository, studentRepository, bookRepository, currentUserProvider);
    }

    @Test
    void createsTheLoanAndUpdatesStockAndBorrowedCount() {
        Book book = new Book();
        book.setStockQuantity(3);
        Student student = new Student();
        student.setBorrowedBooksCount(0);

        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(book));
        when(studentRepository.findByIdAndNotDeleted(2L)).thenReturn(Optional.of(student));
        when(loanRepository.findActiveByStudentId(2L)).thenReturn(List.of());
        when(loanRepository.save(any(Loan.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Loan result = useCase().execute(1L, 2L);

        assertThat(result.getBook()).isSameAs(book);
        assertThat(result.getStudent()).isSameAs(student);
        assertThat(book.getStockQuantity()).isEqualTo(2);
        assertThat(student.getBorrowedBooksCount()).isEqualTo(1);
        assertThat(result.getLimitDate()).isAfter(LocalDateTime.now());
    }

    @Test
    void throwsWhenBookNotFound() {
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase().execute(1L, 2L))
                .isInstanceOf(BookNotFoundException.class);
    }

    @Test
    void throwsWhenStudentNotFound() {
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(new Book()));
        when(studentRepository.findByIdAndNotDeleted(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase().execute(1L, 2L))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void throwsWhenStudentReachedBorrowLimit() {
        Student student = new Student();
        student.setBorrowedBooksCount(12);
        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(new Book()));
        when(studentRepository.findByIdAndNotDeleted(2L)).thenReturn(Optional.of(student));

        assertThatThrownBy(() -> useCase().execute(1L, 2L))
                .isInstanceOf(StudentBorrowLimitReachedException.class);
    }

    @Test
    void throwsWhenStudentHasOverdueLoan() {
        Student student = new Student();
        student.setBorrowedBooksCount(1);
        Loan overdueLoan = new Loan();
        overdueLoan.setLimitDate(LocalDateTime.now().minusDays(1));

        when(bookRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(new Book()));
        when(studentRepository.findByIdAndNotDeleted(2L)).thenReturn(Optional.of(student));
        when(loanRepository.findActiveByStudentId(2L)).thenReturn(List.of(overdueLoan));

        assertThatThrownBy(() -> useCase().execute(1L, 2L))
                .isInstanceOf(LoanOverdueException.class);
    }
}
