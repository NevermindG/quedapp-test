package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budget;

import java.util.UUID;

public interface CreateBudgetUseCase {

    BudgetResult create(
            UUID userId,
            CreateBudgetCommand command
    );
}
