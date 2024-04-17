package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.LoanResponseDto;
import com.bibliproject.biblioteca.domain.entity.Book;
import com.bibliproject.biblioteca.domain.entity.Loan;
import com.bibliproject.biblioteca.domain.entity.Student;
import com.bibliproject.biblioteca.domain.mapper.BookMapper;
import com.bibliproject.biblioteca.domain.mapper.LoanMapper;
import com.bibliproject.biblioteca.domain.mapper.StudentMapper;
import com.bibliproject.biblioteca.repository.BookRepository;
import com.bibliproject.biblioteca.repository.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    //private final BookRepository bookRepository;

    private final StudentService studentService;
    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, BookService bookService, StudentService studentService) {
        this.bookService = bookService;
        this.loanRepository = loanRepository;
        //this.bookRepository = bookRepository;
        this.studentService = studentService;
    }

    public List < LoanResponseDto > findAll() {
        List < Loan > loans = loanRepository.findAll();

        return LoanMapper.toDtoListWithoutLoans(loans);
    }

    public LoanResponseDto findById(long id) {        return LoanMapper.toDtoWithoutLoans(loanRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Loan not found with id: " + id)));

    }

    public LoanResponseDto create(LoanRequestDto loanRequestDto) {

        Book book = BookMapper.toEntity(bookService.findById(loanRequestDto.getBookId()));
        Student student = StudentMapper.toEntityWithoutLoans(studentService.findById(loanRequestDto.getStudentId()));

        if (book.getStockQuantity() <= 0) {
            throw new IllegalArgumentException("O livro não está no estoque.");
        }

        book.setStockQuantity(book.getStockQuantity() - 1);
        Loan loan = LoanMapper.dtoRequestToEntity(loanRequestDto);
        loan.setBook(book);
        student.getLoans().add(loan);
        loan.setStudent(student);
        //loan.getStudent().setLoans(loanRepository.findLoansByStudentId(student.getId()));
        loanRepository.saveAndFlush(loan);

        return LoanMapper.toDtoWithoutLoans(loan);

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