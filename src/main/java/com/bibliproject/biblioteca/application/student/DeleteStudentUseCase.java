package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.exception.student.StudentHaveDebtException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DeleteStudentUseCase {

    private final StudentRepository studentRepository;

    public DeleteStudentUseCase(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void execute(Long id) {
        Student student = studentRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("O estudante com o id: %d não foi encontrado.  ", id)));

        if (student.getBorrowedBooksCount() > 0) {
            throw new StudentHaveDebtException(
                    "O estudante possui livros pendentes de devolução, que devem ser entregues para que o registro seja deletado.");
        }

        student.markDeleted();
        studentRepository.save(student);
    }
}
