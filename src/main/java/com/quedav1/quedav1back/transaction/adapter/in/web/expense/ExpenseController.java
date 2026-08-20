package com.quedav1.quedav1back.transaction.adapter.in.web.expense;

import com.quedav1.quedav1back.transaction.application.port.in.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {

    private final CreateExpenseUseCase createExpenseUseCase;
    private final GetExpensesUseCase getExpensesUseCase;
    private final GetExpenseUseCase getExpenseUseCase;
    private final DeleteExpenseUseCase deleteExpenseUseCase;
    private final UpdateExpenseUseCase updateExpenseUseCase;

    public ExpenseController(
            CreateExpenseUseCase createExpenseUseCase, GetExpensesUseCase getExpensesUseCase, GetExpenseUseCase getExpenseUseCase, DeleteExpenseUseCase deleteExpenseUseCase, UpdateExpenseUseCase updateExpenseUseCase
    ) {
        this.createExpenseUseCase = createExpenseUseCase;
        this.getExpensesUseCase = getExpensesUseCase;
        this.getExpenseUseCase = getExpenseUseCase;
        this.deleteExpenseUseCase = deleteExpenseUseCase;
        this.updateExpenseUseCase = updateExpenseUseCase;
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> create(
            @Valid @RequestBody CreateExpenseRequest request,
            Authentication authentication
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        CreateExpenseCommand command =
                new CreateExpenseCommand(
                        userId,
                        request.amount(),
                        request.currency(),
                        request.description(),
                        request.category(),
                        request.occurredAt()
                );

        ExpenseResult result =
                createExpenseUseCase.create(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ExpenseResponse.from(result));
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getExpenses(
            Authentication authentication
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        List<ExpenseResult> results =
                getExpensesUseCase.getExpenses(userId);

        List<ExpenseResponse> response =
                results.stream()
                        .map(ExpenseResponse::from)
                        .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{expenseId}")
    public ResponseEntity<ExpenseResponse> getExpense(
            @PathVariable UUID expenseId,
            Authentication authentication
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        ExpenseResult result =
                getExpenseUseCase.getExpense(
                        expenseId,
                        userId
                );

        return ResponseEntity.ok(
                ExpenseResponse.from(result)
        );
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable UUID expenseId,
            Authentication authentication
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        deleteExpenseUseCase.deleteExpense(
                expenseId,
                userId
        );

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{expenseId}")
    public ResponseEntity<ExpenseResult> updateExpense(
            @PathVariable UUID expenseId,
            @RequestBody UpdateExpenseRequest request,
            Authentication authentication
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        UpdateExpenseCommand command =
                new UpdateExpenseCommand(
                        expenseId,
                        userId,
                        request.amount(),
                        request.currency(),
                        request.description(),
                        request.category(),
                        request.occurredAt()
                );

        ExpenseResult result =
                updateExpenseUseCase.update(command);

        return ResponseEntity.ok(result);
    }
}