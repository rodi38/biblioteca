package com.bibliproject.biblioteca.application.auth;

import com.bibliproject.biblioteca.domain.auth.AuthenticatedPrincipal;
import com.bibliproject.biblioteca.domain.auth.TokenProvider;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.domain.user.User;
import com.bibliproject.biblioteca.domain.user.UserRepository;
import com.bibliproject.biblioteca.exception.user.InvalidCredentialsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenProvider tokenProvider;

    private LoginUseCase useCase() {
        return new LoginUseCase(userRepository, studentRepository, passwordEncoder, tokenProvider);
    }

    @Test
    void returnsTokenWithStudentIdClaimWhenStudent() {
        User user = new User(1L, new Email("ana@escola.com"), "hashed", Role.STUDENT, null, null);
        Student student = new Student();
        student.setId(5L);

        when(userRepository.findByEmail("ana@escola.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("senha1234", "hashed")).thenReturn(true);
        when(studentRepository.findByUserId(1L)).thenReturn(Optional.of(student));
        when(tokenProvider.generate(any(AuthenticatedPrincipal.class))).thenReturn("a-token");

        String token = useCase().execute("ana@escola.com", "senha1234");

        assertThat(token).isEqualTo("a-token");
    }

    @Test
    void throwsWhenEmailNotFound() {
        when(userRepository.findByEmail("ana@escola.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase().execute("ana@escola.com", "senha1234"))
                .isInstanceOf(InvalidCredentialsException.class);
    }

    @Test
    void throwsWhenPasswordDoesNotMatch() {
        User user = new User(1L, new Email("ana@escola.com"), "hashed", Role.STUDENT, null, null);
        when(userRepository.findByEmail("ana@escola.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("senhaErrada", "hashed")).thenReturn(false);

        assertThatThrownBy(() -> useCase().execute("ana@escola.com", "senhaErrada"))
                .isInstanceOf(InvalidCredentialsException.class);
    }
}
