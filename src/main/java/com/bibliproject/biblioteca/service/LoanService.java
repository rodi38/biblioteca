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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return LoanMapper.INSTANCE.convertEntityListToListResponseDto(loanRepository.findAll());
    }

    public LoanResponseDto findById(long id) {
        return LoanMapper.INSTANCE.convertEntityToResponseDto(loanRepository.findById(id).get());
    }

    public ResponseEntity<Loan> create(LoanRequestDto loanRequestDto) {
        System.out.println(loanRequestDto.getStudent().getId());
        System.out.println(loanRequestDto.getBook().getId());
        Book book = BookMapper.INSTANCE.convertDtoResponseToEntity(bookService.findById(loanRequestDto.getBook().getId()));
        //Student student = StudentMapper.INSTANCE.convertDtoToResponseEntity((studentService.findById(loanRequestDto.getStudent().getId())));


//
//          if (student == null) {
//              return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//          }

//          if (book.getStockQuantity() <= 0){
//              System.out.println("book getquantity");
////              return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
//          }


//          if (book != null){
        book.setStockQuantity(book.getStockQuantity() - 1);
        var loan = new Loan();
//              loan.setBook(book);
//              loan.setStudent(student);
        BeanUtils.copyProperties(loanRequestDto, loan);
        bookService.updateBook(book.getId(), BookMapper.INSTANCE.convertEntityToRequestDto(book));

//              System.out.println(loan.getId() + " ID VAR LOAN");
//              System.out.println(loan.getStudent().getId() + "ID VAR STUDENT LOAN");
//              System.out.println(loanRequestDto.getStudent().getId() + "ID loanRequestDto LOAN");
        // = LoanMapper.INSTANCE.convertDtoToEntity(loanRequestDto);
//              loan.getStudent().setId(student.getId());
//              System.out.println(loan.getId());
        loanRepository.saveAndFlush(loan);

        return ResponseEntity.status(HttpStatus.CREATED).body(loan);
//          }


//        return null;
    }

    public LoanResponseDto update(long id, LoanRequestDto loanRequestDto) {
        Loan loan = LoanMapper.INSTANCE.convertDtoRequestToEntity(loanRequestDto);

        loan.setId(id);

        loanRepository.save(loan);

        return LoanMapper.INSTANCE.convertEntityToResponseDto(loan);
    }

    public boolean delete(long id) {

        loanRepository.deleteById(id);

        return true;
    }
}
