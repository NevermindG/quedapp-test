package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.PlannedExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetPlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.PlannedExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.out.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpense;

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
