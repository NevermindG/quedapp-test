package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense;

import java.util.List;
import java.util.UUID;

public interface GetPlannedExpensesUseCase {

    List<PlannedExpenseResult> getPlannedExpenses(UUID userId);
}
