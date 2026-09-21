package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.GetPlannedExpensesUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.PlannedExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;

import java.util.List;
import java.util.UUID;

public class GetPlannedExpensesService
        implements GetPlannedExpensesUseCase {

    private final PlannedExpenseRepository plannedExpenseRepository;

    public GetPlannedExpensesService(
            PlannedExpenseRepository plannedExpenseRepository
    ) {
        this.plannedExpenseRepository = plannedExpenseRepository;
    }

    @Override
    public List<PlannedExpenseResult> getPlannedExpenses(UUID userId) {

        return plannedExpenseRepository
                .findByUserId(userId)
                .stream()
                .map(PlannedExpenseResult::from)
                .toList();
    }
}
