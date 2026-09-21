package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving;

import java.util.UUID;

public interface GetSavingsGoalUseCase {

    SavingsGoalResult getSavingsGoal(
            UUID savingsGoalId,
            UUID userId
    );
}
