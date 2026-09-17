package com.bibliproject.biblioteca.interfaces.rest;

import com.bibliproject.biblioteca.application.auth.LoginUseCase;
import com.bibliproject.biblioteca.application.auth.RegisterUseCase;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.exception.user.EmailAlreadyInUseException;
import com.bibliproject.biblioteca.exception.user.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterUseCase registerUseCase;

    @MockBean
    private LoginUseCase loginUseCase;

    private static Student aStudent() {
        Student student = new Student();
        student.setId(1L);
        student.setFullName("Ana Silva");
        student.setEmail(new Email("ana@escola.com"));
        return student;
    }

    @Test
    void registerReturns201WhenSuccessful() throws Exception {
        when(registerUseCase.execute(anyString(), any(Email.class), anyString())).thenReturn(aStudent());

        String body = "{\"fullName\":\"Ana Silva\",\"email\":\"ana@escola.com\",\"password\":\"senha1234\"}";
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void registerReturns400WhenBodyFailsValidation() throws Exception {
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void registerReturns409WhenEmailAlreadyInUse() throws Exception {
        when(registerUseCase.execute(anyString(), any(Email.class), anyString()))
                .thenThrow(new EmailAlreadyInUseException("Já existe uma conta com esse email."));

        String body = "{\"fullName\":\"Ana Silva\",\"email\":\"ana@escola.com\",\"password\":\"senha1234\"}";
        mockMvc.perform(post("/auth/register").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void loginReturns200WithTokenWhenSuccessful() throws Exception {
        when(loginUseCase.execute("ana@escola.com", "senha1234")).thenReturn("a-token");

        String body = "{\"email\":\"ana@escola.com\",\"password\":\"senha1234\"}";
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.token").value("a-token"));
    }

    @Test
    void loginReturns401WhenCredentialsAreInvalid() throws Exception {
        when(loginUseCase.execute("ana@escola.com", "wrong")).thenThrow(new InvalidCredentialsException("Email ou senha inválidos."));

        String body = "{\"email\":\"ana@escola.com\",\"password\":\"wrong\"}";
        mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.success").value(false));
    }
}
