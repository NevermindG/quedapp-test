package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.exception.InvalidCredentialsException;
import com.quedav1.quedav1back.transaction.application.port.in.LoginCommand;
import com.quedav1.quedav1back.transaction.application.port.in.LoginResult;
import com.quedav1.quedav1back.transaction.application.port.in.LoginUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.PasswordEncoder;
import com.quedav1.quedav1back.transaction.application.port.out.TokenProvider;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.domain.model.User;

public class LoginService implements LoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    public LoginService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenProvider tokenProvider
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public LoginResult login(LoginCommand command) {

        User user = userRepository
                .findByEmail(command.email())
                .orElseThrow(InvalidCredentialsException::new);

        boolean passwordMatches = passwordEncoder.matches(
                command.password(),
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new InvalidCredentialsException();
        }

        String accessToken = tokenProvider.generateToken(user);

        return new LoginResult(
                user.getId(),
                accessToken
        );
    }
}