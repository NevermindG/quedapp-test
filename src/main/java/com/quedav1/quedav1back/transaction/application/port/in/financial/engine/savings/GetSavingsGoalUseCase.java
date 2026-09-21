package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

import java.util.UUID;

public interface GetSavingsGoalUseCase {

    SavingsGoalResult getSavingsGoal(
            UUID savingsGoalId,
            UUID userId
    );
}
