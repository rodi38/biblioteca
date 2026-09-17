package com.bibliproject.biblioteca.application.loan;

import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
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

    public ReturnLoanUseCase(LoanRepository loanRepository, BookRepository bookRepository,
                              StudentRepository studentRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Loan execute(Long id) {
        Loan loan = loanRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new LoanNotFoundException("Empréstimo com o id: " + id + " não foi encontrado."));

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
