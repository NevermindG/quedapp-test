package com.quedav1.quedav1back.transaction.application.port.in;

public record LoginCommand(
        String email,
        String password
) {
}
