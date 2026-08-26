package com.quedav1.quedav1back.transaction.adapter.in.web.financial.savinggoal;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/savings-goals")
public class SavingsGoalController {

    private final CreateSavingsGoalUseCase createSavingsGoalUseCase;
    private final CreateSavingsContributionUseCase createSavingsContributionUseCase;

    public SavingsGoalController(
            CreateSavingsGoalUseCase createSavingsGoalUseCase, CreateSavingsContributionUseCase createSavingsContributionUseCase
    ) {
        this.createSavingsGoalUseCase = createSavingsGoalUseCase;
        this.createSavingsContributionUseCase = createSavingsContributionUseCase;
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
}
