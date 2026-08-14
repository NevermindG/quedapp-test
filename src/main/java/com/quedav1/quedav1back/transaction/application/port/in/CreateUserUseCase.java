package com.quedav1.quedav1back.transaction.application.port.in;

public interface CreateUserUseCase {

    UserResult create(CreateUserCommand createUserCommand);
}
