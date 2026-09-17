package com.bibliproject.biblioteca.infrastructure.persistence.user;

import com.bibliproject.biblioteca.domain.student.Email;
import com.bibliproject.biblioteca.domain.user.User;

public final class UserPersistenceMapper {

    private UserPersistenceMapper() {
    }

    public static User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                new Email(entity.getEmail()),
                entity.getPasswordHash(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public static UserJpaEntity toJpaEntity(User user) {
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(user.getId());
        entity.setEmail(user.getEmail().value());
        entity.setPasswordHash(user.getPasswordHash());
        entity.setRole(user.getRole());
        entity.setCreatedAt(user.getCreatedAt());
        entity.setUpdatedAt(user.getUpdatedAt());
        return entity;
    }
}
