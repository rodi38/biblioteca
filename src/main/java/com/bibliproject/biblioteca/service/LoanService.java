package com.bibliproject.biblioteca.service;

import com.bibliproject.biblioteca.domain.dto.request.LoanRequestDto;
import com.bibliproject.biblioteca.domain.dto.response.CustomResponse;
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

    public CustomResponse create(LoanRequestDto loanRequestDto) {
        CustomResponse response = new CustomResponse();

        try {
            Book book = BookMapper.toEntity(bookService.findById(loanRequestDto.getBookId()));
            Student student = StudentMapper.simpleStudentResponseToEntity(studentService.findById(loanRequestDto.getStudentId()));

            if (book.getStockQuantity() <= 0) {
                throw new IllegalArgumentException();
            }

            book.setStockQuantity(book.getStockQuantity() - 1);
            Loan loan = new Loan();



            if (student.getLoans() != null) {
                loan.setLimitDate(setLoanLimitData(new Date()));
                loan.setBook(book);
                loan.setStudent(student);
                student.getLoans().add(loan);
                if (!canStudentBorrow(student.getLoans())) {
                    throw new IllegalStateException();
                }
            } else  {
                student.setLoans(List.of(loan));
            }

            bookRepository.save(book);
            studentRepository.save(student);

            loanRepository.save(loan);
            response.setSuccess(true);
            response.setMessage("Empréstimo efetuado com sucesso.");
            response.setData(LoanMapper.toSimpleLoanResponse(loan));

        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage("O livro não está no estoque.");
        } catch (IllegalStateException e ) {
            response.setSuccess(false);
            response.setMessage("O estudante tem livros atrasados. Não é possivel fazer nenhum emprestimo enquanto a devolução não for efetuada.");
        }



        return response;
    }

    public SimpleLoanResponse update(long id) {
        Loan loan = LoanMapper.simpleLoanResponseToEntity(findById(id));
        loan.setReturnDate(new Date());
        loan.getBook().setStockQuantity(loan.getBook().getStockQuantity() + 1);
        bookRepository.save(loan.getBook());
        loanRepository.save(loan);

        return LoanMapper.toSimpleLoanResponse(loan);
    }

    public boolean delete(long id) {
        Loan loan = LoanMapper.simpleLoanResponseToEntity(findById(id));
        loanRepository.delete(loan);
        return true;
    }

    public Date setLoanLimitData(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, 9);
        return cal.getTime();
    }

    public boolean canStudentBorrow(List<Loan> loans) {
        Date currentDate = new Date();
        return loans.stream().noneMatch(loan -> loan.getReturnDate() == null && currentDate.after(loan.getLimitDate()));
    }
}