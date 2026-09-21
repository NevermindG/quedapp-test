package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budgets;

import java.util.UUID;

public interface UpdateBudgetUseCase {

    BudgetResult update(
            UUID budgetId,
            UUID userId,
            UpdateBudgetCommand command
    );
}
