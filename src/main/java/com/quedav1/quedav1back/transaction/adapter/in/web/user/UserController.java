package com.quedav1.quedav1back.transaction.adapter.in.web.user;

import com.quedav1.quedav1back.transaction.adapter.in.web.auth.LoginRequest;
import com.quedav1.quedav1back.transaction.adapter.in.web.auth.LoginResponse;
import com.quedav1.quedav1back.transaction.application.port.in.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final LoginUseCase loginUseCase;
    private final GetCurrentUserUseCase getCurrentUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase, LoginUseCase loginUseCase, GetCurrentUserUseCase getCurrentUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.loginUseCase = loginUseCase;
        this.getCurrentUserUseCase = getCurrentUserUseCase;
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

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {

        LoginResult result = loginUseCase.login(
                new LoginCommand(
                        request.email(),
                        request.password()
                )
        );

        return ResponseEntity.ok(
                new LoginResponse(
                        result.accessToken()
                )
        );
    }

    @GetMapping("/me")
    public ResponseEntity<CurrentUserResult> getCurrentUser(
            Authentication authentication
    ) {

        UUID userId = (UUID) authentication.getPrincipal();
        String firstName = authentication.getName();

        CurrentUserResult result =
                getCurrentUserUseCase.getCurrentUser(userId);

        return ResponseEntity.ok(result);
    }
}
