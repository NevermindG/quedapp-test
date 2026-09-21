package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.application.exception.PlannedExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.GetPlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.PlannedExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpense;

import java.util.UUID;

public class GetPlannedExpenseService
        implements GetPlannedExpenseUseCase {

    private final PlannedExpenseRepository plannedExpenseRepository;

    public GetPlannedExpenseService(
            PlannedExpenseRepository plannedExpenseRepository
    ) {
        this.plannedExpenseRepository =
                plannedExpenseRepository;
    }

    @Override
    public PlannedExpenseResult get(
            UUID plannedExpenseId,
            UUID userId
    ) {

        PlannedExpense plannedExpense =
                plannedExpenseRepository
                        .findByIdAndUserId(
                                plannedExpenseId,
                                userId
                        )
                        .orElseThrow(
                                PlannedExpenseNotFoundException::new
                        );

        return PlannedExpenseResult.from(
                plannedExpense
        );
    }
}
