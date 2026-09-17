package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateStudentUseCase {

    private final StudentRepository studentRepository;

    public UpdateStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student execute(Long id, String fullName, Email email) {
        Student student = studentRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("O estudante com o id: %d não foi encontrado.  ", id)));

        student.setFullName(fullName);
        student.setEmail(email);
        student.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(student);
    }
}
