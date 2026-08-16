package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.CurrentUserResult;
import com.quedav1.quedav1back.transaction.application.port.in.GetCurrentUserUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.domain.model.User;

import java.util.UUID;

public class GetCurrentUserService implements GetCurrentUserUseCase {

    private final UserRepository userRepository;

    public GetCurrentUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CurrentUserResult getCurrentUser(UUID userId) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new CurrentUserResult(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getCurrency(),
                user.getTimezone()
        );
    }
}