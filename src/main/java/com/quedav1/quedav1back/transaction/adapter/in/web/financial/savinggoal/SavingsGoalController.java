package com.quedav1.quedav1back.transaction.adapter.in.web.financial.savinggoal;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/savings-goals")
public class SavingsGoalController {

    private final CreateSavingsGoalUseCase createSavingsGoalUseCase;
    private final CreateSavingsContributionUseCase createSavingsContributionUseCase;
    private final GetSavingsGoalsUseCase getSavingsGoalsUseCase;
    private final GetSavingsGoalUseCase getSavingsGoalUseCase;
    private final GetSavingsContributionsUseCase getSavingsContributionsUseCase;
    private final CancelSavingsGoalUseCase cancelSavingsGoalUseCase;

    public SavingsGoalController(
            CreateSavingsGoalUseCase createSavingsGoalUseCase, CreateSavingsContributionUseCase createSavingsContributionUseCase, GetSavingsGoalsUseCase getSavingsGoalsUseCase, GetSavingsGoalUseCase getSavingsGoalUseCase, GetSavingsContributionsUseCase getSavingsContributionsUseCase, CancelSavingsGoalUseCase cancelSavingsGoalUseCase
    ) {
        this.createSavingsGoalUseCase = createSavingsGoalUseCase;
        this.createSavingsContributionUseCase = createSavingsContributionUseCase;
        this.getSavingsGoalsUseCase = getSavingsGoalsUseCase;
        this.getSavingsGoalUseCase = getSavingsGoalUseCase;
        this.getSavingsContributionsUseCase = getSavingsContributionsUseCase;
        this.cancelSavingsGoalUseCase = cancelSavingsGoalUseCase;
    }

    @PostMapping
    public ResponseEntity<SavingsGoalResult> create(
            @RequestBody CreateSavingsGoalRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        SavingsGoalResult result =
                createSavingsGoalUseCase.create(
                        new CreateSavingsGoalCommand(
                                userId,
                                request.name(),
                                request.targetAmount(),
                                request.currency(),
                                request.targetDate()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @PostMapping("/{savingsGoalId}/contributions")
    public ResponseEntity<SavingsContributionResult> addContribution(
            @PathVariable UUID savingsGoalId,
            @RequestBody CreateSavingsContributionRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        SavingsContributionResult result =
                createSavingsContributionUseCase.create(
                        new CreateSavingsContributionCommand(
                                savingsGoalId,
                                userId,
                                request.amount(),
                                request.date()
                        )
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<SavingsGoalResult>> getSavingsGoals(
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        return ResponseEntity.ok(
                getSavingsGoalsUseCase
                        .getSavingsGoals(userId)
        );
    }

    @GetMapping("/{savingsGoalId}")
    public ResponseEntity<SavingsGoalResult> getSavingsGoal(
            @PathVariable UUID savingsGoalId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        SavingsGoalResult result =
                getSavingsGoalUseCase.getSavingsGoal(
                        savingsGoalId,
                        userId
                );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{savingsGoalId}/contributions")
    public ResponseEntity<List<SavingsContributionHistoricalResult>>
    getContributions(
            @PathVariable UUID savingsGoalId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        List<SavingsContributionHistoricalResult> result =
                getSavingsContributionsUseCase
                        .getContributions(
                                savingsGoalId,
                                userId
                        );

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{savingsGoalId}/cancel")
    public ResponseEntity<SavingsGoalResult> cancelSavingsGoal(
            @PathVariable UUID savingsGoalId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        SavingsGoalResult result =
                cancelSavingsGoalUseCase.cancel(
                        savingsGoalId,
                        userId
                );

        return ResponseEntity.ok(result);
    }
}
