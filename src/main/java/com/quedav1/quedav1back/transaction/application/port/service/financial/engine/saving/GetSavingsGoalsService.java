package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.saving;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving.GetSavingsGoalsUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving.SavingsGoalResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsGoalRepository;

import java.util.List;
import java.util.UUID;

public class GetSavingsGoalsService
        implements GetSavingsGoalsUseCase {

    private final SavingsGoalRepository savingsGoalRepository;

    public GetSavingsGoalsService(
            SavingsGoalRepository savingsGoalRepository
    ) {
        this.savingsGoalRepository =
                savingsGoalRepository;
    }

    @Override
    public List<SavingsGoalResult> getSavingsGoals(UUID userId) {
        return savingsGoalRepository
                .findByUserId(userId)
                .stream()
                .map(SavingsGoalResult::from)
                .toList();
    }
}
