package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.saving;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.CreateSavingsContributionCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.CreateSavingsContributionUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.SavingsContributionResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsContributionRepository;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsGoalRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsContribution;
import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsGoal;

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
