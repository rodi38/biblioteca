package com.bibliproject.biblioteca.application.auth;

import com.bibliproject.biblioteca.domain.auth.AuthenticatedPrincipal;
import com.bibliproject.biblioteca.domain.auth.TokenProvider;
import com.bibliproject.biblioteca.domain.student.StudentRepository;
import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.domain.user.User;
import com.bibliproject.biblioteca.domain.user.UserRepository;
import com.bibliproject.biblioteca.exception.user.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginUseCase {

    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public LoginUseCase(UserRepository userRepository, StudentRepository studentRepository,
                         PasswordEncoder passwordEncoder, TokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    public String execute(String email, String rawPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Email ou senha inválidos."));

        if (!passwordEncoder.matches(rawPassword, user.getPasswordHash())) {
            throw new InvalidCredentialsException("Email ou senha inválidos.");
        }

        Long studentId = user.getRole() == Role.STUDENT
                ? studentRepository.findByUserId(user.getId()).map(student -> student.getId()).orElse(null)
                : null;

        AuthenticatedPrincipal principal = new AuthenticatedPrincipal(
                user.getId(), user.getEmail().value(), user.getRole(), studentId);
        return tokenProvider.generate(principal);
    }
}
