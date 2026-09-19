package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import java.util.List;
import java.util.UUID;

public interface GetSavingsGoalsUseCase {

    List<SavingsGoalResult> getSavingsGoals(UUID userId);
}