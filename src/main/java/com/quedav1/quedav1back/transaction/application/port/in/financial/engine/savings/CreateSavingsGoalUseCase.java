package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

public interface CreateSavingsGoalUseCase {

    SavingsGoalResult create(CreateSavingsGoalCommand command);
}
