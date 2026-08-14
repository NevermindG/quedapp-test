package com.quedav1.quedav1back.transaction.adapter.out.persistence.user;

import com.quedav1.quedav1back.transaction.domain.model.User;


public final class UserPersistenceMapper {

    private UserPersistenceMapper(){}

    public static UserJpaEntity toEntity(User user) {
        return new UserJpaEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPasswordHash(),
                user.getCurrency(),
                user.getTimezone(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static User toDomain(UserJpaEntity entity) {
        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPasswordHash(),
                entity.getCurrency(),
                entity.getTimezone(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
