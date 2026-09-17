package com.bibliproject.biblioteca.application.auth;

import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.student.Student;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.domain.user.User;
import com.bibliproject.biblioteca.domain.user.UserRepository;
import com.bibliproject.biblioteca.exception.user.EmailAlreadyInUseException;
import com.bibliproject.biblioteca.exception.user.InvalidPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RegisterUseCase {

    private static final int MIN_PASSWORD_LENGTH = 8;

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUseCase(UserRepository userRepository, StudentRepository studentRepository,
                            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Student execute(String fullName, Email email, String rawPassword) {
        if (rawPassword == null || rawPassword.length() < MIN_PASSWORD_LENGTH) {
            throw new InvalidPasswordException(
                    "A senha deve ter pelo menos " + MIN_PASSWORD_LENGTH + " caracteres.");
        }
        if (userRepository.existsByEmail(email.value())) {
            throw new EmailAlreadyInUseException("Já existe uma conta com o email: " + email.value());
        }

        User user = new User(null, email, passwordEncoder.encode(rawPassword), Role.STUDENT, null, null);
        User savedUser = userRepository.save(user);

        Student student = new Student();
        student.setUserId(savedUser.getId());
        student.setFullName(fullName);
        student.setEmail(email);
        student.setBorrowedBooksCount(0);

        return studentRepository.save(student);
    }
}
