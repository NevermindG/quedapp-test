package com.quedav1.quedav1back.transaction.adapter.in.web.user;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

import java.util.UUID;

public record CreateUserRequest (
        UUID id,
        String firstName,
        String lastName,
        String email,
        String password,
        Currency currency,
        String timezone
) {
}
