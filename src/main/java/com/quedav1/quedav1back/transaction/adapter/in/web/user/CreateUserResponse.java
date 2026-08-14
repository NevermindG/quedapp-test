package com.quedav1.quedav1back.transaction.adapter.in.web.user;

import com.quedav1.quedav1back.transaction.application.port.in.UserResult;

import java.util.UUID;

public record CreateUserResponse (
        UUID id,
        String firstname,
        String lastName
) {
    public static CreateUserResponse from (UserResult result) {
        return new CreateUserResponse(
                result.id(),
                result.firstName(),
                result.lastName()
        );
    }
}
