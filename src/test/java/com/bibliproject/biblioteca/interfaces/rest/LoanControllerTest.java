package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.loan.CreateLoanUseCase;
import com.bibliproject.biblioteca.application.loan.DeleteLoanUseCase;
import com.bibliproject.biblioteca.application.loan.FindLoanUseCase;
import com.bibliproject.biblioteca.application.loan.ListDeletedLoansUseCase;
import com.bibliproject.biblioteca.application.loan.ListLoansUseCase;
import com.bibliproject.biblioteca.application.loan.ReturnLoanUseCase;
import com.bibliproject.biblioteca.domain.book.Book;
import com.bibliproject.biblioteca.domain.book.Isbn;
import com.bibliproject.biblioteca.domain.loan.Loan;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.exception.book.BookAlreadyReturnedException;
import com.bibliproject.biblioteca.exception.loan.LoanNotFoundException;
import com.bibliproject.biblioteca.exception.loan.LoanOverdueException;
import com.bibliproject.biblioteca.exception.student.StudentBorrowLimitReachedException;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListLoansUseCase listLoansUseCase;

    @MockBean
    private ListDeletedLoansUseCase listDeletedLoansUseCase;

    @MockBean
    private FindLoanUseCase findLoanUseCase;

    @MockBean
    private CreateLoanUseCase createLoanUseCase;

    @MockBean
    private ReturnLoanUseCase returnLoanUseCase;

    @MockBean
    private DeleteLoanUseCase deleteLoanUseCase;

    private static final String VALID_BODY = "{\"bookId\":1,\"studentId\":2}";

    private static Loan aLoan() {
        Book book = new Book();
        book.setId(1L);
        book.setIsbn(new Isbn("123"));

        Student student = new Student();
        student.setId(2L);
        student.setEmail(new Email("a@b.com"));

        Loan loan = new Loan();
        loan.setId(10L);
        loan.setBook(book);
        loan.setStudent(student);
        loan.setLoanDate(LocalDateTime.now());
        loan.setLimitDate(LocalDateTime.now().plusDays(9));
        return loan;
    }

    @Test
    void createReturns201WhenSuccessful() throws Exception {
        when(createLoanUseCase.execute(1L, 2L)).thenReturn(aLoan());

        mockMvc.perform(post("/loan").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void createReturns400WhenBodyFailsValidation() throws Exception {
        mockMvc.perform(post("/loan").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void createReturns400WhenStudentReachedBorrowLimit() throws Exception {
        when(createLoanUseCase.execute(1L, 2L)).thenThrow(new StudentBorrowLimitReachedException("limite atingido"));

        mockMvc.perform(post("/loan").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void createReturns401WhenStudentHasOverdueLoan() throws Exception {
        when(createLoanUseCase.execute(1L, 2L)).thenThrow(new LoanOverdueException("empréstimo em atraso"));

        mockMvc.perform(post("/loan").contentType(MediaType.APPLICATION_JSON).content(VALID_BODY))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void updateReturns200WhenSuccessful() throws Exception {
        when(returnLoanUseCase.execute(10L)).thenReturn(aLoan());

        mockMvc.perform(put("/loan/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void updateReturns400WhenAlreadyReturned() throws Exception {
        when(returnLoanUseCase.execute(10L)).thenThrow(new BookAlreadyReturnedException("Este livro já foi retornado."));

        mockMvc.perform(put("/loan/10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void findByIdReturns404WhenLoanNotFound() throws Exception {
        when(findLoanUseCase.execute(10L)).thenThrow(new LoanNotFoundException("Empréstimo não encontrado."));

        mockMvc.perform(get("/loan/10"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void deleteReturnsNoContentWhenSuccessful() throws Exception {
        mockMvc.perform(delete("/loan/10"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteReturns400WhenStudentHasDebt() throws Exception {
        org.mockito.Mockito.doThrow(new StudentHaveDebtException("O livro ainda não foi retornado."))
                .when(deleteLoanUseCase).execute(anyLong());

        mockMvc.perform(delete("/loan/10"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }
}
