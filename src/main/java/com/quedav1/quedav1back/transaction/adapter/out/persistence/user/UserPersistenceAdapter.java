package com.quedav1.quedav1back.transaction.adapter.out.persistence.user;

import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.domain.model.User;

import java.util.Optional;

public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = UserPersistenceMapper.toEntity(user);

        System.out.println("3 - entity.password: " + entity.getPasswordHash());


        UserJpaEntity saved = repository.save(entity);
        return UserPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }
}
