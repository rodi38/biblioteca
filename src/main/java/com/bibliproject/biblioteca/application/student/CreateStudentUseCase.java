package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import org.springframework.stereotype.Component;

@Component
public class CreateStudentUseCase {

    private final StudentRepository studentRepository;

    public CreateStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student execute(Student student) {
        return studentRepository.save(student);
    }
}
