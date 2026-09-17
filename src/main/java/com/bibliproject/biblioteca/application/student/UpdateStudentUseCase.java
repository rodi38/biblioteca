package com.bibliproject.biblioteca.application.student;

import com.bibliproject.biblioteca.domain.auth.CurrentUserProvider;
import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.exception.auth.ForbiddenOperationException;
import com.bibliproject.biblioteca.exception.student.StudentNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UpdateStudentUseCase {

    private final StudentRepository studentRepository;
    private final CurrentUserProvider currentUserProvider;

    public UpdateStudentUseCase(StudentRepository studentRepository, CurrentUserProvider currentUserProvider) {
        this.studentRepository = studentRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public Student execute(Long id, String fullName, Email email) {
        Student student = studentRepository.findByIdAndNotDeleted(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        String.format("O estudante com o id: %d não foi encontrado.  ", id)));

        var currentUser = currentUserProvider.getCurrentUser();
        if (!currentUser.isAdmin() && !currentUser.ownsStudent(id)) {
            throw new ForbiddenOperationException("Você só pode editar o seu próprio cadastro.");
        }

        student.setFullName(fullName);
        student.setEmail(email);
        student.setUpdatedAt(LocalDateTime.now());

        return studentRepository.save(student);
    }
}
