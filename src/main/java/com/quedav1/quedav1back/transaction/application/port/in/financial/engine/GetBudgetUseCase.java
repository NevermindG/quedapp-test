package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import java.util.UUID;

public interface GetBudgetUseCase {

    BudgetResult getBudget(
            UUID budgetId,
            UUID userId
    );
}
