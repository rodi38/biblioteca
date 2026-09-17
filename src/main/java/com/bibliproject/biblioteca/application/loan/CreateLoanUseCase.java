package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.exception.auth.ForbiddenOperationException;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import com.bibliproject.biblioteca.exception.loan.LoanOverdueException;
import com.bibliproject.biblioteca.exception.student.StudentBorrowLimitReachedException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class CreateLoanUseCase {

    private static final int MAX_BORROWED_BOOKS = 12;
    private static final int LOAN_PERIOD_DAYS = 9;

    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    private final CurrentUserProvider currentUserProvider;

    public CreateLoanUseCase(LoanRepository loanRepository, StudentRepository studentRepository,
                              BookRepository bookRepository, CurrentUserProvider currentUserProvider) {
        this.loanRepository = loanRepository;
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @Transactional
    public Loan execute(Long bookId, Long studentId) {
        var currentUser = currentUserProvider.getCurrentUser();
        if (!currentUser.isAdmin() && !currentUser.ownsStudent(studentId)) {
            throw new ForbiddenOperationException("Você só pode criar empréstimos para o seu próprio cadastro.");
        }

        Book book = bookRepository.findByIdAndNotDeleted(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        Student student = studentRepository.findByIdAndNotDeleted(studentId)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("O estudante com o id: %d não foi encontrado.  ", studentId)));

        if (student.getBorrowedBooksCount() >= MAX_BORROWED_BOOKS) {
            throw new StudentBorrowLimitReachedException(
                    "Este estudante já tem o máximo de livros emprestados(12), deve devolver algum para pegar outro.");
        }

        boolean hasOverdueLoan = loanRepository.findActiveByStudentId(studentId).stream()
                .anyMatch(Loan::isOverdue);
        if (hasOverdueLoan) {
            throw new LoanOverdueException(
                    "O estudante possui livros pendentes de devolução, que devem ser entregues para que ele possa pegar mais livros emprestados.");
        }

        book.decreaseStock();
        student.setBorrowedBooksCount(student.getBorrowedBooksCount() + 1);
        student.setUpdatedAt(LocalDateTime.now());

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setStudent(student);
        loan.setLoanDate(LocalDateTime.now());
        loan.setLimitDate(LocalDateTime.now().plusDays(LOAN_PERIOD_DAYS));

        bookRepository.save(book);
        studentRepository.save(student);
        return loanRepository.save(loan);
    }
}
