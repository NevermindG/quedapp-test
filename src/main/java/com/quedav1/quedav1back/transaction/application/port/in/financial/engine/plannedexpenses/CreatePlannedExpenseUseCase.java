package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses;

public interface CreatePlannedExpenseUseCase {

    PlannedExpenseResult create(
            CreatePlannedExpenseCommand command
    );
}
