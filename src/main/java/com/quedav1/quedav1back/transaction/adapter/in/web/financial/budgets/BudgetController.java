package com.quedav1.quedav1back.transaction.adapter.in.web.financial.budgets;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budgets.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {

    private final CreateBudgetUseCase createBudgetUseCase;
    private final GetCurrentBudgetsUseCase getCurrentBudgetsUseCase;
    private final GetBudgetUseCase getBudgetUseCase;
    private final UpdateBudgetUseCase updateBudgetUseCase;
    private final DeleteBudgetUseCase deleteBudgetUseCase;

    public BudgetController(
            CreateBudgetUseCase createBudgetUseCase, GetCurrentBudgetsUseCase getCurrentBudgetsUseCase, GetBudgetUseCase getBudgetUseCase, UpdateBudgetUseCase updateBudgetUseCase, DeleteBudgetUseCase deleteBudgetUseCase
    ) {
        this.createBudgetUseCase =
                createBudgetUseCase;
        this.getCurrentBudgetsUseCase = getCurrentBudgetsUseCase;
        this.getBudgetUseCase = getBudgetUseCase;
        this.updateBudgetUseCase = updateBudgetUseCase;
        this.deleteBudgetUseCase = deleteBudgetUseCase;
    }

    @PostMapping
    public ResponseEntity<BudgetResult> create(
            @RequestBody CreateBudgetRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        CreateBudgetCommand command =
                new CreateBudgetCommand(
                        request.category(),
                        request.amount(),
                        request.currency(),
                        request.year(),
                        request.month()
                );

        BudgetResult result =
                createBudgetUseCase.create(
                        userId,
                        command
                );

        return ResponseEntity
                .created(
                        URI.create(
                                "/api/v1/budgets/"
                                        + result.id()
                        )
                )
                .body(result);
    }

    @GetMapping("/current")
    public ResponseEntity<List<CurrentBudgetResult>>
    getCurrentBudgets(
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(
                getCurrentBudgetsUseCase
                        .getCurrentBudgets(userId)
        );
    }

    @GetMapping("/{budgetId}")
    public ResponseEntity<BudgetResult> getBudget(
            @PathVariable UUID budgetId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        BudgetResult result =
                getBudgetUseCase.getBudget(
                        budgetId,
                        userId
                );

        return ResponseEntity.ok(
                result
        );
    }

    @PutMapping("/{budgetId}")
    public ResponseEntity<BudgetResult> updateBudget(
            @PathVariable UUID budgetId,
            @RequestBody UpdateBudgetRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        UpdateBudgetCommand command =
                new UpdateBudgetCommand(
                        request.amount()
                );

        BudgetResult result =
                updateBudgetUseCase.update(
                        budgetId,
                        userId,
                        command
                );

        return ResponseEntity.ok(
                result
        );
    }

    @DeleteMapping("/{budgetId}")
    public ResponseEntity<Void> deleteBudget(
            @PathVariable UUID budgetId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        deleteBudgetUseCase.delete(
                budgetId,
                userId
        );

        return ResponseEntity.noContent().build();
    }

}
