package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses;

import java.util.UUID;

public interface DeletePlannedExpenseUseCase {

    void delete(
            UUID plannedExpenseId,
            UUID userId
    );
}
