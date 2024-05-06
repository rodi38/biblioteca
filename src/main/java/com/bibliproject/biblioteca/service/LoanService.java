package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.loan.SimpleLoanResponse;
import com.bibliproject.biblioteca.domain.dto.simple.response.loan.SimpleLoanResponseStudent;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.domain.mapper.LoanMapper;
import com.bibliproject.biblioteca.domain.mapper.StudentMapper;
import com.bibliproject.biblioteca.exception.book.BookAlreadyReturnedException;
import com.bibliproject.biblioteca.exception.book.BookOutOfStockException;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import com.bibliproject.biblioteca.exception.loan.LoanOverdueException;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import com.bibliproject.biblioteca.repository.BookRepository;
import com.bibliproject.biblioteca.repository.LoanRepository;
import com.bibliproject.biblioteca.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    private final StudentService studentService;
    private final BookService bookService;


    public Page<SimpleLoanResponseStudent> findAll(String search, Pageable pageable) {
        Page<Loan> loans;
        if (search != null) {
            loans = loanRepository.findAllNotDeletedAndMatchesSearch(search, pageable);
        } else  {
            loans = loanRepository.findAllNotDeleted(pageable);
        }
        return loans.map(LoanMapper::toSimpleLoanResponseStudent);
    }

    public SimpleLoanResponse findById(long id) {
        return LoanMapper.toSimpleLoanResponse(loanRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found with id: " + id)));

    }

    public SimpleLoanResponse create(LoanRequestDto loanRequestDto) {
        Book book = BookMapper.toEntity(bookService.findById(loanRequestDto.getBookId()));
        Student student = StudentMapper.simpleStudentResponseToEntity(studentService.findById(loanRequestDto.getStudentId()));

        if (book.getStockQuantity() <= 0) {
            throw new BookOutOfStockException("Livro fora de estoque.");
        }
        book.setStockQuantity(book.getStockQuantity() - 1);
        Loan loan = new Loan();


        if (student.getLoans() != null) {
            loan.setLimitDate(setLoanLimitData(LocalDateTime.now()));
            loan.setBook(book);
            loan.setStudent(student);
            student.getLoans().add(loan);
            if (!canStudentBorrow(student.getLoans())) {
                throw new LoanOverdueException("This student has some overdue books, they need to return the books before borrowing again.");
            }
        } else {
            student.setLoans(List.of(loan));
        }

        student.setBorrowedBooksCount(student.getBorrowedBooksCount() + 1);
        loan.setBook(book);
        loan.setStudent(student);
        bookRepository.save(book);
        studentRepository.save(student);
        loanRepository.save(loan);

        return LoanMapper.toSimpleLoanResponse(loan);
    }

    public SimpleLoanResponse update(long id) {
        Loan loan = LoanMapper.simpleLoanResponseToEntity(findById(id));
        if (loan.getReturnDate() != null) {
            throw new BookAlreadyReturnedException("This book has already been returned");
        }
        loan.setReturnDate(LocalDateTime.now());
        loan.getBook().setStockQuantity(loan.getBook().getStockQuantity() + 1);
        loan.getStudent().setBorrowedBooksCount(loan.getStudent().getBorrowedBooksCount() - 1);
        loan.getBook().setUpdatedAt(LocalDateTime.now());
        loan.getStudent().setUpdatedAt(LocalDateTime.now());
        studentRepository.save(loan.getStudent());
        bookRepository.save(loan.getBook());
        loanRepository.save(loan);

        return LoanMapper.toSimpleLoanResponse(loan);
    }

    public void delete(long id) {
        Loan loan = LoanMapper.simpleLoanResponseToEntity(findById(id));
        if (loan.getReturnDate() == null){
            throw new StudentHaveDebtException("Student have barrowed books, return them to delete.");
        }
        loan.setDeleted(true);
        loan.setDeletedAt(LocalDateTime.now());
        loanRepository.save(loan);
    }

    public LocalDateTime setLoanLimitData(LocalDateTime currentDateTime) {
        return currentDateTime.plusDays(9);
    }

    public boolean canStudentBorrow(List<Loan> loans) {
        LocalDateTime currentDate = LocalDateTime.now();
        return loans.stream().noneMatch(loan -> loan.getReturnDate() == null && currentDate.isAfter(loan.getLimitDate()));
    }
}