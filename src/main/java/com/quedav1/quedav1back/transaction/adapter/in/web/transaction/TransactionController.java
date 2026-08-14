package com.quedav1.quedav1back.transaction.adapter.in.web.transaction;

import com.quedav1.quedav1back.transaction.application.port.in.CreateTransactionCommand;
import com.quedav1.quedav1back.transaction.application.port.in.CreateTransactionUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.TransactionResult;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;

    public TransactionController(
            CreateTransactionUseCase createTransactionUseCase
    ) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> create(
            @Valid @RequestBody CreateTransactionRequest request
    ) {

        CreateTransactionCommand command =
                new CreateTransactionCommand(
                        request.userId(),
                        request.amount(),
                        request.type(),
                        request.description(),
                        request.date()
                );

        TransactionResult result =
                createTransactionUseCase.create(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TransactionResponse.from(result));
    }
}
