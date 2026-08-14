package com.quedav1.quedav1back.transaction.application.port.in;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

public record CreateUserCommand(
        String firstName,
        String lastName,
        String email,
        String password,
        Currency currency,
        String timezone
) {}
