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
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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

//    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, StudentRepository studentRepository,  BookService bookService, StudentService studentService ) {
//        this.bookService = bookService;
//        this.loanRepository = loanRepository;
//        this.studentRepository = studentRepository;
//        this.bookRepository = bookRepository;
//        //this.bookRepository = bookRepository;
//        this.studentService = studentService;
//    }

    public List < LoanResponseDto > findAll() {
        List < Loan > loans = loanRepository.findAll();

        return LoanMapper.toDtoListWithoutLoans(loans);
    }

    public LoanResponseDto findById(long id) {        return LoanMapper.toDtoWithoutLoans(loanRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id)));

    }

    public SimpleLoanResponse create(LoanRequestDto loanRequestDto) {

        Book book = BookMapper.toEntity(bookService.findById(loanRequestDto.getBookId()));
        Student student = StudentMapper.toEntityWithoutLoans(studentService.findById(loanRequestDto.getStudentId()));
        if (book.getStockQuantity() <= 0) {
            throw new IllegalArgumentException("O livro não está no estoque.");
        }

        book.setStockQuantity(book.getStockQuantity() - 1);
        bookRepository.save(book);

        Loan loan = LoanMapper.dtoRequestToEntity(loanRequestDto);
        loan.setBook(book);
        loan.setStudent(student);


        if (student.getLoans() != null) {
            student.getLoans().add(loan);
            //studentRepository.saveAndFlush(student);
        } else  {
            student.setLoans(List.of(loan));
        }
        studentRepository.save(student);
        //bookRepository.save(book);
        System.out.println(student.getId() + " " + student.getFullName() + " " + student.getEmail() + " " + student.getLoans().get(0));
//        loan.getStudent());
        loanRepository.save(loan);

        //SimpleLoanResponse response =


        return LoanMapper.toSimpleLoanResponse(loan);

    }

    public LoanResponseDto update(long id, LoanRequestDto loanRequestDto) {
        LoanResponseDto loanResponseDto = findById(id);
        loanResponseDto.setLoanDate(loanRequestDto.getLoanDate());
        loanResponseDto.setReturnDate(loanRequestDto.getReturnDate());
        loanRepository.save(LoanMapper.toEntityWithoutLoans(loanResponseDto));

        return loanResponseDto;
    }

    public boolean delete(long id) {
        Loan loan = LoanMapper.toEntityWithoutLoans(findById(id));
        loanRepository.delete(loan);
        return true;
    }
}