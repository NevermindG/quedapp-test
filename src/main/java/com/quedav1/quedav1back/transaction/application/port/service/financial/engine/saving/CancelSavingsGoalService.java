package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.saving;

import com.quedav1.quedav1back.transaction.application.exception.SavingsGoalNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving.CancelSavingsGoalUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving.SavingsGoalResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsGoalRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.saving.SavingsGoal;

import java.util.UUID;

public class CancelSavingsGoalService
        implements CancelSavingsGoalUseCase {

    private final SavingsGoalRepository savingsGoalRepository;

    public CancelSavingsGoalService(
            SavingsGoalRepository savingsGoalRepository
    ) {
        this.savingsGoalRepository = savingsGoalRepository;
    }

    @Override
    public SavingsGoalResult cancel(
            UUID savingsGoalId,
            UUID userId
    ) {

        SavingsGoal savingsGoal =
                savingsGoalRepository
                        .findByIdAndUserId(
                                savingsGoalId,
                                userId
                        )
                        .orElseThrow(
                                SavingsGoalNotFoundException::new
                        );

        SavingsGoal cancelledGoal =
                savingsGoal.cancel();

        SavingsGoal savedGoal =
                savingsGoalRepository.save(
                        cancelledGoal
                );

        return SavingsGoalResult.from(
                savedGoal
        );
    }
}
