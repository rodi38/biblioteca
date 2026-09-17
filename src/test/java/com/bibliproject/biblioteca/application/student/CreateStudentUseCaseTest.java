package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateStudentUseCaseTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    void savesAndReturnsTheStudent() {
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        Student result = new CreateStudentUseCase(studentRepository).execute(student);

        assertThat(result).isSameAs(student);
        verify(studentRepository).save(student);
    }
}
