package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import java.util.UUID;

public interface UpdatePlannedExpenseUseCase {

    PlannedExpenseResult update(
            UUID plannedExpenseId,
            UUID userId,
            UpdatePlannedExpenseCommand command
    );
}
