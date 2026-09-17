package com.bibliproject.biblioteca.domain.auth;

import com.bibliproject.biblioteca.domain.user.Role;

public record AuthenticatedPrincipal(Long userId, String email, Role role, Long studentId) {

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean ownsStudent(Long candidateStudentId) {
        return studentId != null && studentId.equals(candidateStudentId);
    }
}
