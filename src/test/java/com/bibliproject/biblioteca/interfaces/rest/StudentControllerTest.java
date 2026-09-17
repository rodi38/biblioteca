package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.student.CreateStudentUseCase;
import com.bibliproject.biblioteca.application.student.DeleteStudentUseCase;
import com.bibliproject.biblioteca.application.student.FindStudentUseCase;
import com.bibliproject.biblioteca.application.student.ListDeletedStudentsUseCase;
import com.bibliproject.biblioteca.application.student.ListStudentsUseCase;
import com.bibliproject.biblioteca.application.student.UpdateStudentUseCase;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

    private static Student aStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setFullName("Ana Silva");
        student.setEmail(new Email("ana@escola.com"));
        return student;
    }

    @Test
    void findByIdReturnsStudentWhenFound() throws Exception {
        when(findStudentUseCase.execute(1L)).thenReturn(aStudent());

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void findByIdReturns404WhenStudentNotFound() throws Exception {
        when(findStudentUseCase.execute(1L)).thenThrow(new StudentNotFoundException("não encontrado"));

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void findByIdReturns400WhenConstraintIsViolated() throws Exception {
        when(findStudentUseCase.execute(1L)).thenThrow(new ConstraintViolationException("id inválido", Set.of()));

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void createReturns400WhenBodyFailsValidation() throws Exception {
        String invalidBody = "{\"fullName\":\"\",\"email\":\"not-an-email\"}";

        mockMvc.perform(post("/student").contentType(MediaType.APPLICATION_JSON).content(invalidBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errors").isArray());
    }
}
