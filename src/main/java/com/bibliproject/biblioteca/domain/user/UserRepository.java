package com.bibliproject.biblioteca.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsAnyWithRole(Role role);

    User save(User user);
}
