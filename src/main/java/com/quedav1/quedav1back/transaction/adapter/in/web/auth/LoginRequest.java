package com.quedav1.quedav1back.transaction.adapter.in.web.auth;

public record LoginRequest(
        String email,
        String password
) {
}
