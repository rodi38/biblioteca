package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.book.CreateBookUseCase;
import com.bibliproject.biblioteca.application.book.DeleteBookUseCase;
import com.bibliproject.biblioteca.application.book.FindBookUseCase;
import com.bibliproject.biblioteca.application.book.ListBooksUseCase;
import com.bibliproject.biblioteca.application.book.ListDeletedBooksUseCase;
import com.bibliproject.biblioteca.application.book.UpdateBookUseCase;
import com.bibliproject.biblioteca.application.loan.CreateLoanUseCase;
import com.bibliproject.biblioteca.application.loan.DeleteLoanUseCase;
import com.bibliproject.biblioteca.application.loan.FindLoanUseCase;
import com.bibliproject.biblioteca.application.loan.ListDeletedLoansUseCase;
import com.bibliproject.biblioteca.application.loan.ListLoansUseCase;
import com.bibliproject.biblioteca.application.loan.ReturnLoanUseCase;
import com.bibliproject.biblioteca.application.student.CreateStudentUseCase;
import com.bibliproject.biblioteca.application.student.DeleteStudentUseCase;
import com.bibliproject.biblioteca.application.student.FindStudentUseCase;
import com.bibliproject.biblioteca.application.student.ListDeletedStudentsUseCase;
import com.bibliproject.biblioteca.application.student.ListStudentsUseCase;
import com.bibliproject.biblioteca.application.student.UpdateStudentUseCase;
import com.bibliproject.biblioteca.domain.auth.TokenProvider;
import com.bibliproject.biblioteca.infrastructure.security.SecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({BookController.class, StudentController.class, LoanController.class})
@Import(SecurityConfig.class)
class SecurityAuthorizationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private ListBooksUseCase listBooksUseCase;
    @MockBean
    private ListDeletedBooksUseCase listDeletedBooksUseCase;
    @MockBean
    private FindBookUseCase findBookUseCase;
    @MockBean
    private CreateBookUseCase createBookUseCase;
    @MockBean
    private UpdateBookUseCase updateBookUseCase;
    @MockBean
    private DeleteBookUseCase deleteBookUseCase;

    @MockBean
    private ListStudentsUseCase listStudentsUseCase;
    @MockBean
    private ListDeletedStudentsUseCase listDeletedStudentsUseCase;
    @MockBean
    private FindStudentUseCase findStudentUseCase;
    @MockBean
    private CreateStudentUseCase createStudentUseCase;
    @MockBean
    private UpdateStudentUseCase updateStudentUseCase;
    @MockBean
    private DeleteStudentUseCase deleteStudentUseCase;

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

    @BeforeEach
    @SuppressWarnings({"unchecked", "rawtypes"})
    void stubListingsToReturnEmptyPages() {
        Page emptyPage = Page.empty(PageRequest.of(0, 5));
        when(listBooksUseCase.execute(any(), any())).thenReturn(emptyPage);
        when(listDeletedBooksUseCase.execute(any(), any())).thenReturn(emptyPage);
        when(listStudentsUseCase.execute(any(), any())).thenReturn(emptyPage);
        when(listDeletedStudentsUseCase.execute(any(), any())).thenReturn(emptyPage);
        when(listLoansUseCase.execute(any(), any())).thenReturn(emptyPage);
        when(listDeletedLoansUseCase.execute(any(), any())).thenReturn(emptyPage);
    }

    @Test
    void anonymousRequestToBookCatalogIsRejectedWith401() throws Exception {
        mockMvc.perform(get("/book")).andExpect(status().isUnauthorized());
    }

    @Test
    void anonymousRequestToBookAuditIsRejectedWith401() throws Exception {
        mockMvc.perform(get("/book/audit")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void studentCannotDeleteBooks() throws Exception {
        mockMvc.perform(delete("/book/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanDeleteBooks() throws Exception {
        mockMvc.perform(delete("/book/1")).andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void studentCannotAccessBookAudit() throws Exception {
        mockMvc.perform(get("/book/audit")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanAccessBookAudit() throws Exception {
        mockMvc.perform(get("/book/audit")).andExpect(status().isOk());
    }

    @Test
    void anonymousRequestToStudentListIsRejectedWith401() throws Exception {
        mockMvc.perform(get("/student")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void studentCannotListAllStudents() throws Exception {
        mockMvc.perform(get("/student")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanListAllStudents() throws Exception {
        mockMvc.perform(get("/student")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void studentCannotDeleteStudents() throws Exception {
        mockMvc.perform(delete("/student/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanDeleteStudents() throws Exception {
        mockMvc.perform(delete("/student/1")).andExpect(status().isNoContent());
    }

    @Test
    void anonymousRequestToLoanAuditIsRejectedWith401() throws Exception {
        mockMvc.perform(get("/loan/audit")).andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void studentCannotAccessLoanAudit() throws Exception {
        mockMvc.perform(get("/loan/audit")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanAccessLoanAudit() throws Exception {
        mockMvc.perform(get("/loan/audit")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    void studentCannotDeleteLoans() throws Exception {
        mockMvc.perform(delete("/loan/1")).andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminCanDeleteLoans() throws Exception {
        mockMvc.perform(delete("/loan/1")).andExpect(status().isNoContent());
    }
}
