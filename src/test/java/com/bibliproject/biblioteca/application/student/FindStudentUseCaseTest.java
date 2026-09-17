package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindStudentUseCaseTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    void returnsTheStudentWhenFound() {
        Student student = new Student();
        when(studentRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(student));

        Student result = new FindStudentUseCase(studentRepository).execute(1L);

        assertThat(result).isSameAs(student);
    }

    @Test
    void throwsWhenNotFound() {
        when(studentRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new FindStudentUseCase(studentRepository).execute(1L))
                .isInstanceOf(StudentNotFoundException.class);
    }
}
