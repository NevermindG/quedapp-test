package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.SavingsContributionNotFoundException;
import com.quedav1.quedav1back.transaction.application.exception.SavingsGoalNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetSavingsContributionsUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.SavingsContributionHistoricalResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.SavingsContributionResult;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsContributionRepository;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsGoalRepository;

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