package com.quedav1.quedav1back.transaction.application.port.out;

import com.quedav1.quedav1back.transaction.domain.model.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);
}
