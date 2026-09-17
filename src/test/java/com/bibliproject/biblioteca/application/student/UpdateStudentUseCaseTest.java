package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.auth.AuthenticatedPrincipal;
import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateStudentUseCaseTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CurrentUserProvider currentUserProvider;

    @Test
    void updatesFullNameAndEmailAndSaves() {
        Student student = new Student();
        when(studentRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(currentUserProvider.getCurrentUser()).thenReturn(new AuthenticatedPrincipal(9L, "admin@a.com", Role.ADMIN, null));

        Student result = new UpdateStudentUseCase(studentRepository, currentUserProvider)
                .execute(1L, "Name", new Email("a@b.com"));

        assertThat(result.getFullName()).isEqualTo("Name");
        assertThat(result.getEmail().value()).isEqualTo("a@b.com");
        assertThat(result.getUpdatedAt()).isNotNull();
    }

    @Test
    void throwsWhenStudentNotFound() {
        when(studentRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new UpdateStudentUseCase(studentRepository, currentUserProvider)
                .execute(1L, "Name", new Email("a@b.com")))
                .isInstanceOf(StudentNotFoundException.class);
    }
}
