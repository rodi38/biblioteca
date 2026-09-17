package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteStudentUseCaseTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    void marksTheStudentAsDeletedWhenNoBooksBorrowed() {
        Student student = new Student();
        student.setBorrowedBooksCount(0);
        when(studentRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(student));

        new DeleteStudentUseCase(studentRepository).execute(1L);

        assertThat(student.isDeleted()).isTrue();
        verify(studentRepository).save(student);
    }

    @Test
    void throwsWhenStudentNotFound() {
        when(studentRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> new DeleteStudentUseCase(studentRepository).execute(1L))
                .isInstanceOf(StudentNotFoundException.class);
    }

    @Test
    void throwsWhenStudentHasBorrowedBooks() {
        Student student = new Student();
        student.setBorrowedBooksCount(1);
        when(studentRepository.findByIdAndNotDeleted(1L)).thenReturn(Optional.of(student));

        assertThatThrownBy(() -> new DeleteStudentUseCase(studentRepository).execute(1L))
                .isInstanceOf(StudentHaveDebtException.class);
    }
}
