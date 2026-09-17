package com.bibliproject.biblioteca.infrastructure.security;

import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.domain.user.User;
import com.bibliproject.biblioteca.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final String adminEmail;
    private final String adminPassword;

    public AdminSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder,
                        @Value("${admin.seed.email}") String adminEmail,
                        @Value("${admin.seed.password}") String adminPassword) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.existsAnyWithRole(Role.ADMIN)) {
            return;
        }

        User admin = new User(null, new Email(adminEmail), passwordEncoder.encode(adminPassword), Role.ADMIN, null, null);
        userRepository.save(admin);
    }
}
