package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.CreateUserCommand;
import com.quedav1.quedav1back.transaction.application.port.in.CreateUserUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.UserResult;
import com.quedav1.quedav1back.transaction.application.port.out.PasswordEncoder;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.domain.model.User;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResult create(CreateUserCommand createUserCommand) {

        String passwordHash =
                passwordEncoder.encode(createUserCommand.password());

        System.out.println("1 - password: " + passwordHash);

        Instant now = Instant.now();

        User user = new User(
                UUID.randomUUID(),
                createUserCommand.firstName(),
                createUserCommand.lastName(),
                createUserCommand.email(),
                passwordHash,
                createUserCommand.currency(),
                createUserCommand.timezone(),
                now,
                now
        );

        System.out.println("2 - user.password: " + user.getPasswordHash());

        User savedUser = userRepository.save(user);

        return new UserResult(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName()
        );
    }
}
