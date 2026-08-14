package com.quedav1.quedav1back.transaction.adapter.in.web.user;

import com.quedav1.quedav1back.transaction.application.port.in.CreateUserCommand;
import com.quedav1.quedav1back.transaction.application.port.in.CreateUserUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.UserResult;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> create (
            @Valid @RequestBody CreateUserRequest request
    ) {

        CreateUserCommand command =
                new CreateUserCommand(
                        request.firstName(),
                        request.lastName(),
                        request.email(),
                        request.password(),
                        request.currency(),
                        request.timezone()
                );

        UserResult result =
                createUserUseCase.create(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(CreateUserResponse.from(result));
    }
}
