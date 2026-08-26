package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreateSavingsContributionCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreateSavingsContributionUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.SavingsContributionResult;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsContributionRepository;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsGoalRepository;
import com.quedav1.quedav1back.transaction.domain.model.saving.SavingsContribution;
import com.quedav1.quedav1back.transaction.domain.model.saving.SavingsGoal;

import java.time.Instant;
import java.util.UUID;

public class CreateSavingsContributionService
        implements CreateSavingsContributionUseCase {

    private final SavingsGoalRepository savingsGoalRepository;
    private final SavingsContributionRepository contributionRepository;

    public CreateSavingsContributionService(
            SavingsGoalRepository savingsGoalRepository,
            SavingsContributionRepository contributionRepository
    ) {
        this.savingsGoalRepository = savingsGoalRepository;
        this.contributionRepository = contributionRepository;
    }

    @Override
    public SavingsContributionResult create(
            CreateSavingsContributionCommand command
    ) {

        SavingsGoal goal = savingsGoalRepository
                .findByIdAndUserId(
                        command.savingsGoalId(),
                        command.userId()
                )
                .orElseThrow(() ->
                        new RuntimeException("Savings goal not found")
                );

        SavingsContribution contribution =
                new SavingsContribution(
                        UUID.randomUUID(),
                        goal.getId(),
                        command.userId(),
                        command.amount(),
                        command.date(),
                        Instant.now()
                );

        SavingsGoal updatedGoal =
                goal.contribute(command.amount());

        SavingsContribution savedContribution =
                contributionRepository.save(contribution);

        savingsGoalRepository.save(updatedGoal);

        return new SavingsContributionResult(
                savedContribution.getId(),
                savedContribution.getSavingsGoalId(),
                savedContribution.getAmount(),
                savedContribution.getDate(),
                updatedGoal.getCurrentAmount(),
                updatedGoal.getStatus()
        );
    }
}
