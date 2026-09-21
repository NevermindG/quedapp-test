package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.saving;

import com.quedav1.quedav1back.transaction.application.exception.SavingsContributionNotFoundException;
import com.quedav1.quedav1back.transaction.application.exception.SavingsGoalNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving.GetSavingsContributionsUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving.SavingsContributionHistoricalResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsContributionRepository;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsGoalRepository;

import java.util.List;
import java.util.UUID;

public class GetSavingsContributionsService
        implements GetSavingsContributionsUseCase {

    private final SavingsGoalRepository savingsGoalRepository;
    private final SavingsContributionRepository savingsContributionRepository;

    public GetSavingsContributionsService(
            SavingsGoalRepository savingsGoalRepository,
            SavingsContributionRepository savingsContributionRepository
    ) {
        this.savingsGoalRepository =
                savingsGoalRepository;

        this.savingsContributionRepository =
                savingsContributionRepository;
    }

    @Override
    public List<SavingsContributionHistoricalResult> getContributions(
            UUID savingsGoalId,
            UUID userId
    ) {

        savingsGoalRepository
                .findByIdAndUserId(
                        savingsGoalId,
                        userId
                )
                .orElseThrow(
                        SavingsGoalNotFoundException::new
                );

        List<SavingsContributionHistoricalResult> contributions =
                savingsContributionRepository
                        .findBySavingsGoalIdAndUserId(
                                savingsGoalId,
                                userId
                        )
                        .stream()
                        .map(SavingsContributionHistoricalResult::from)
                        .toList();

        if (contributions.isEmpty()) {
            throw new SavingsContributionNotFoundException();
        }

        return contributions;
    }
}