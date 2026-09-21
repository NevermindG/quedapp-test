package com.quedav1.quedav1back.transaction.adapter.in.web.financial.plannedexpense;

import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/planned-expenses")
public class PlannedExpenseController {

    private final CreatePlannedExpenseUseCase createPlannedExpenseUseCase;
    private final GetPlannedExpensesUseCase getPlannedExpensesUseCase;
    private final PayPlannedExpenseUseCase payPlannedExpenseUseCase;
    private final GetPlannedExpenseUseCase getPlannedExpenseUseCase;
    private final UpdatePlannedExpenseUseCase updatePlannedExpenseUseCase;
    private final DeletePlannedExpenseUseCase deletePlannedExpenseUseCase;

    public PlannedExpenseController(
            CreatePlannedExpenseUseCase createPlannedExpenseUseCase, GetPlannedExpensesUseCase getPlannedExpensesUseCase, PayPlannedExpenseUseCase payPlannedExpenseUseCase, GetPlannedExpenseUseCase getPlannedExpenseUseCase, UpdatePlannedExpenseUseCase updatePlannedExpenseUseCase, DeletePlannedExpenseUseCase deletePlannedExpenseUseCase
    ) {
        this.createPlannedExpenseUseCase =
                createPlannedExpenseUseCase;
        this.getPlannedExpensesUseCase = getPlannedExpensesUseCase;
        this.payPlannedExpenseUseCase = payPlannedExpenseUseCase;
        this.getPlannedExpenseUseCase = getPlannedExpenseUseCase;
        this.updatePlannedExpenseUseCase = updatePlannedExpenseUseCase;
        this.deletePlannedExpenseUseCase = deletePlannedExpenseUseCase;
    }

    @PostMapping
    public ResponseEntity<PlannedExpenseResult> create(
            @RequestBody CreatePlannedExpenseRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        CreatePlannedExpenseCommand command =
                new CreatePlannedExpenseCommand(
                        userId,
                        request.amount(),
                        request.currency(),
                        request.description(),
                        request.category(),
                        request.dueDate()
                );

        PlannedExpenseResult result =
                createPlannedExpenseUseCase.create(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<PlannedExpenseResult>> getPlannedExpenses(
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        List<PlannedExpenseResult> result =
                getPlannedExpensesUseCase.getPlannedExpenses(userId);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{plannedExpenseId}/pay")
    public ResponseEntity<ExpenseResult> pay(
            @PathVariable UUID plannedExpenseId,
            @RequestBody PayPlannedExpenseRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        ExpenseResult result =
                payPlannedExpenseUseCase.pay(
                        plannedExpenseId,
                        userId,
                        request.paidAt()
                );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{plannedExpenseId}")
    public ResponseEntity<PlannedExpenseResult> getPlannedExpense(
            @PathVariable UUID plannedExpenseId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(
                getPlannedExpenseUseCase.get(
                        plannedExpenseId,
                        userId
                )
        );
    }

    @PutMapping("/{plannedExpenseId}")
    public ResponseEntity<PlannedExpenseResult> updatePlannedExpense(
            @PathVariable UUID plannedExpenseId,
            @RequestBody UpdatePlannedExpenseRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        UpdatePlannedExpenseCommand command =
                new UpdatePlannedExpenseCommand(
                        request.amount(),
                        request.currency(),
                        request.description(),
                        request.category(),
                        request.dueDate()
                );

        return ResponseEntity.ok(
                updatePlannedExpenseUseCase.update(
                        plannedExpenseId,
                        userId,
                        command
                )
        );
    }

    @DeleteMapping("/{plannedExpenseId}")
    public ResponseEntity<Void> deletePlannedExpense(
            @PathVariable UUID plannedExpenseId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        deletePlannedExpenseUseCase.delete(
                plannedExpenseId,
                userId
        );

        return ResponseEntity
                .noContent()
                .build();
    }
}
