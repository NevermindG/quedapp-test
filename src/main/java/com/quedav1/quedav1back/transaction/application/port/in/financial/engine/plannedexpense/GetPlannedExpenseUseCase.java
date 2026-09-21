package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense;

import java.util.UUID;

public interface GetPlannedExpenseUseCase {

    PlannedExpenseResult get(
            UUID plannedExpenseId,
            UUID userId
    );
}
