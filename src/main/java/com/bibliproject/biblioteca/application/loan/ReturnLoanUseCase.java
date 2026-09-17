package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.exception.auth.ForbiddenOperationException;
import com.bibliproject.biblioteca.exception.book.BookAlreadyReturnedException;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class ReturnLoanUseCase {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final CurrentUserProvider currentUserProvider;

    public ReturnLoanUseCase(LoanRepository loanRepository, BookRepository bookRepository,
                              StudentRepository studentRepository, CurrentUserProvider currentUserProvider) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.currentUserProvider = currentUserProvider;
    }

    @Transactional
    public Loan execute(Long id) {
        Loan loan = loanRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new LoanNotFoundException("Empréstimo com o id: " + id + " não foi encontrado."));

        var currentUser = currentUserProvider.getCurrentUser();
        if (!currentUser.isAdmin() && !currentUser.ownsStudent(loan.getStudent().getId())) {
            throw new ForbiddenOperationException("Você só pode devolver os seus próprios empréstimos.");
        }

        if (loan.getReturnDate() != null) {
            throw new BookAlreadyReturnedException("Este livro já foi retornado.");
        }

        loan.markReturned();
        loan.getBook().increaseStock();
        loan.getStudent().setBorrowedBooksCount(loan.getStudent().getBorrowedBooksCount() - 1);
        loan.getStudent().setUpdatedAt(LocalDateTime.now());

        bookRepository.save(loan.getBook());
        studentRepository.save(loan.getStudent());
        return loanRepository.save(loan);
    }
}
