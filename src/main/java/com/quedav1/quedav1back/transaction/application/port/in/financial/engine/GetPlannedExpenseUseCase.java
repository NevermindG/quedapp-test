package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import java.util.List;
import java.util.UUID;

public interface GetPlannedExpenseUseCase {

    PlannedExpenseResult get(
            UUID plannedExpenseId,
            UUID userId
    );
}
