package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.CreateUserCommand;
import com.quedav1.quedav1back.transaction.application.port.in.CreateUserUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.UserResult;
import com.quedav1.quedav1back.transaction.application.port.out.PasswordEncoder;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserTimezoneValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;

import java.time.Instant;
import java.util.UUID;

public class CreateUserService implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResult create(
            CreateUserCommand createUserCommand
    ) {

        /*
         * Validamos que el timezone sea un IANA timezone válido.
         *
         * Ejemplo:
         * America/Lima
         */
        UserTimezoneValidator.validate(
                createUserCommand.timezone()
        );

        String passwordHash =
                passwordEncoder.encode(
                        createUserCommand.password()
                );

        Instant now =
                Instant.now();

        User user =
                new User(
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

        User savedUser =
                userRepository.save(user);

        return new UserResult(
                savedUser.getId(),
                savedUser.getFirstName(),
                savedUser.getLastName()
        );
    }
}