package com.bibliproject.biblioteca.application.book;

import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.BookRepository;
import com.bibliproject.biblioteca.domain.loan.LoanRepository;
import com.bibliproject.biblioteca.exception.book.BookCurrentlyLoanedException;
import com.bibliproject.biblioteca.exception.book.BookNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DeleteBookUseCase {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public DeleteBookUseCase(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    public void execute(Long id) {
        Book book = bookRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (!loanRepository.findActiveByBookId(id).isEmpty()) {
            throw new BookCurrentlyLoanedException("O livro está emprestado, não é possivel deletar.");
        }

        book.markDeleted();
        bookRepository.save(book);
    }
}
