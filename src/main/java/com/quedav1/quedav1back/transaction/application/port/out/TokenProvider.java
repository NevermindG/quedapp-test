package com.quedav1.quedav1back.transaction.application.port.out;

import com.quedav1.quedav1back.transaction.domain.model.User;

import java.util.UUID;

public interface TokenProvider {
    String generateToken(User user);

    boolean isValid(String token);

    UUID extractUserId(String token);
}
