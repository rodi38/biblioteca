package com.bibliproject.biblioteca.infrastructure.persistence.user;

import com.bibliproject.biblioteca.domain.user.Role;
import com.bibliproject.biblioteca.domain.user.User;
import com.bibliproject.biblioteca.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(UserPersistenceMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }

    @Override
    public boolean existsAnyWithRole(Role role) {
        return userJpaRepository.existsByRole(role);
    }

    @Override
    public User save(User user) {
        UserJpaEntity saved = userJpaRepository.save(UserPersistenceMapper.toJpaEntity(user));
        return UserPersistenceMapper.toDomain(saved);
    }
}
