package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListDeletedStudentsUseCase {

    private final StudentRepository studentRepository;

    public ListDeletedStudentsUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<Student> execute(String search, Pageable pageable) {
        if (search != null) {
            return studentRepository.findAllDeletedAndMatchesSearch(search, pageable);
        }
        return studentRepository.findAllDeleted(pageable);
    }
}
