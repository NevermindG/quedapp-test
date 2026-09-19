package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.SavingsGoalNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetSavingsGoalUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.SavingsGoalResult;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsGoalRepository;

import java.util.UUID;

public class GetSavingsGoalService
        implements GetSavingsGoalUseCase {

    private final SavingsGoalRepository savingsGoalRepository;

    public GetSavingsGoalService(
            SavingsGoalRepository savingsGoalRepository
    ) {
        this.savingsGoalRepository =
                savingsGoalRepository;
    }

    @Override
    public SavingsGoalResult getSavingsGoal(
            UUID savingsGoalId,
            UUID userId
    ) {

        return savingsGoalRepository
                .findByIdAndUserId(
                        savingsGoalId,
                        userId
                )
                .map(SavingsGoalResult::from)
                .orElseThrow(
                        SavingsGoalNotFoundException::new
                );
    }
}
