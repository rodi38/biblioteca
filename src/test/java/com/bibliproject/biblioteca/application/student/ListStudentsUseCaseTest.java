package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListStudentsUseCaseTest {

    @Mock
    private StudentRepository studentRepository;

    @Test
    void delegatesToSearchQueryWhenSearchIsGiven() {
        Pageable pageable = Pageable.unpaged();
        Page<Student> page = new PageImpl<>(java.util.List.of());
        when(studentRepository.findAllNotDeletedAndMatchesSearch("term", pageable)).thenReturn(page);

        Page<Student> result = new ListStudentsUseCase(studentRepository).execute("term", pageable);

        assertThat(result).isSameAs(page);
        verify(studentRepository).findAllNotDeletedAndMatchesSearch("term", pageable);
        verifyNoMoreInteractions(studentRepository);
    }

    @Test
    void delegatesToListAllWhenSearchIsNull() {
        Pageable pageable = Pageable.unpaged();
        Page<Student> page = new PageImpl<>(java.util.List.of());
        when(studentRepository.findAllNotDeleted(pageable)).thenReturn(page);

        Page<Student> result = new ListStudentsUseCase(studentRepository).execute(null, pageable);

        assertThat(result).isSameAs(page);
        verify(studentRepository).findAllNotDeleted(pageable);
        verifyNoMoreInteractions(studentRepository);
    }
}
