package com.quedav1.quedav1back.transaction.application.port.in;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

import java.util.UUID;

public record CurrentUserResult(
        UUID userId,
        String firstName,
        String lastName,
        String email,
        Currency currency,
        String timezone
) {
}
