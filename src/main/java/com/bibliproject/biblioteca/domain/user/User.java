package com.bibliproject.biblioteca.domain.user;

import com.bibliproject.biblioteca.domain.student.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private Long id;
    private Email email;
    private String passwordHash;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
