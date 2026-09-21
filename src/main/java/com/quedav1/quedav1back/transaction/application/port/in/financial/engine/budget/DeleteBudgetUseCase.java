package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budget;

import java.util.UUID;

public interface DeleteBudgetUseCase {

    void delete(
            UUID budgetId,
            UUID userId
    );
}