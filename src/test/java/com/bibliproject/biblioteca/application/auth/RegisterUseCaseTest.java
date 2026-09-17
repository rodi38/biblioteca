package com.bibliproject.biblioteca.application.auth;

import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.domain.user.User;
import com.bibliproject.biblioteca.domain.user.UserRepository;
import com.bibliproject.biblioteca.exception.user.EmailAlreadyInUseException;
import com.bibliproject.biblioteca.exception.user.InvalidPasswordException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private RegisterUseCase useCase() {
        return new RegisterUseCase(userRepository, studentRepository, passwordEncoder);
    }

    @Test
    void createsUserWithStudentRoleAndLinkedStudent() {
        when(userRepository.existsByEmail("ana@escola.com")).thenReturn(false);
        when(passwordEncoder.encode("senha1234")).thenReturn("hashed");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Student result = useCase().execute("Ana Silva", new Email("ana@escola.com"), "senha1234");

        assertThat(result.getUserId()).isEqualTo(1L);
        assertThat(result.getFullName()).isEqualTo("Ana Silva");
        assertThat(result.getEmail().value()).isEqualTo("ana@escola.com");
    }

    @Test
    void throwsWhenEmailAlreadyInUse() {
        when(userRepository.existsByEmail("ana@escola.com")).thenReturn(true);

        assertThatThrownBy(() -> useCase().execute("Ana Silva", new Email("ana@escola.com"), "senha1234"))
                .isInstanceOf(EmailAlreadyInUseException.class);
    }

    @Test
    void throwsWhenPasswordIsTooShort() {
        assertThatThrownBy(() -> useCase().execute("Ana Silva", new Email("ana@escola.com"), "123"))
                .isInstanceOf(InvalidPasswordException.class);
    }
}
