package com.quedav1.quedav1back.transaction.application.port.in;

import java.util.UUID;

public record UserResult(
        UUID id,
        String firstName,
        String lastName
) {
}
