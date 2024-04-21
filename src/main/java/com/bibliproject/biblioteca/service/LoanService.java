package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.dto.simple.response.SimpleLoanResponse;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.domain.mapper.LoanMapper;
import com.bibliproject.biblioteca.domain.mapper.StudentMapper;
import com.bibliproject.biblioteca.repository.BookRepository;
import com.bibliproject.biblioteca.repository.LoanRepository;
import com.bibliproject.biblioteca.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;
    //private final BookRepository bookRepository;

    private final StudentService studentService;
    private final BookService bookService;


    public List < SimpleLoanResponse > findAll() {
        List < Loan > loans = loanRepository.findAll();

        return LoanMapper.toSimpleLoanResponseList(loans);
    }

    public SimpleLoanResponse findById(long id) {
        return LoanMapper.toSimpleLoanResponse(loanRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id)));

    }

    public SimpleLoanResponse create(LoanRequestDto loanRequestDto) {

        Book book = BookMapper.toEntity(bookService.findById(loanRequestDto.getBookId()));
        Student student = StudentMapper.simpleStudentResponseToEntity(studentService.findById(loanRequestDto.getStudentId()));

        System.out.println("before everything student loans: " + student.getLoans());



        if (book.getStockQuantity() <= 0) {
            throw new IllegalArgumentException("O livro não está no estoque.");
        }

        book.setStockQuantity(book.getStockQuantity() - 1);
        Loan loan = LoanMapper.dtoRequestToEntity(loanRequestDto);

        loan.setBook(book);
        loan.setStudent(student);


        if (student.getLoans() != null) {
            loan.setLimitDate(setLoanLimitData(new Date()));
            student.getLoans().add(loan);
            if (!canStudentBorrow(student.getLoans())) {
                throw new IllegalStateException("O estudante tem livros atrasados. Não é possivel fazer nenhum emprestimo enquanto a devolução não for efetuada.");
            }
            //studentRepository.saveAndFlush(student);
        } else  {
            student.setLoans(List.of(loan));
        }

        System.out.println("after everything student loans: " + student.getLoans());
        bookRepository.save(book);
        studentRepository.save(student);


        //bookRepository.save(book);
        System.out.println(student.getId() + " " + student.getFullName() + " " + student.getEmail() + " " + student.getLoans().get(0));
//        loan.getStudent());
        loanRepository.save(loan);

        //SimpleLoanResponse response =


        return LoanMapper.toSimpleLoanResponse(loan);

    }

    public SimpleLoanResponse update(long id) {
        Loan loan = LoanMapper.simpleLoanResponseToEntity(findById(id));
        loan.setReturnDate(new Date());

        loanRepository.save(loan);

        return LoanMapper.toSimpleLoanResponse(loan);
    }

    public boolean delete(long id) {
        Loan loan = LoanMapper.simpleLoanResponseToEntity(findById(id));
        loanRepository.delete(loan);
        return true;
    }

    public Date setLoanLimitData(Date loanData) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(loanData);
        cal.add(Calendar.DATE, 9);
        return cal.getTime();
    }

    public boolean canStudentBorrow(List<Loan> loans) {
        Date currentDate = new Date();
        return loans.stream().noneMatch(loan -> currentDate.after(loan.getLimitDate()));
    }
}