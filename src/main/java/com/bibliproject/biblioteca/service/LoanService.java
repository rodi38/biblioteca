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
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;

    private final StudentService studentService;
    private final BookService bookService;

    public LoanService(LoanRepository loanRepository, BookService bookService, BookRepository bookRepository, StudentService studentService) {
        this.bookService = bookService;
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.studentService = studentService;
    }


    public List<LoanResponseDto> findAll() {
        List<LoanResponseDto> loans = LoanMapper.toDtoList(loanRepository.findAll());

        System.out.println(loans);

        System.out.println("the end total");
        return loans;
    }

    public LoanResponseDto findById(long id) {
        return LoanMapper.toDto(loanRepository.findById(id).get());
    }


    public LoanResponseDto create(LoanRequestDto loanRequestDto) {

        Book book = BookMapper.toEntity(bookService.findById(loanRequestDto.getBook().getId()));
        Student student = StudentMapper.toEntity(studentService.findById(loanRequestDto.getStudent().getId()));

        if (book.getStockQuantity() <= 0) {
            throw new IllegalArgumentException("O livro não está no estoque.");
        }

        book.setStockQuantity(book.getStockQuantity() - 1);
        Loan loan = LoanMapper.dtoRequestToEntity(loanRequestDto);
        loan.setBook(book);
        loan.setStudent(student);


        loanRepository.saveAndFlush(loan);

        return LoanMapper.toDto(loan);

    }

    public LoanResponseDto update(long id, LoanRequestDto loanRequestDto) {
        Loan loan = LoanMapper.dtoRequestToEntity(loanRequestDto);

        loan.setId(id);

        loanRepository.save(loan);

        return LoanMapper.toDto(loan);
    }

    public boolean delete(long id) {

        loanRepository.deleteById(id);

        return true;
    }
}
