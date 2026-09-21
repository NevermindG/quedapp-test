package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses;

import com.quedav1.quedav1back.transaction.application.port.in.expenses.ExpenseResult;

import java.time.LocalDate;
import java.util.UUID;

public interface PayPlannedExpenseUseCase {

    ExpenseResult pay(
            UUID plannedExpenseId,
            UUID userId,
            LocalDate paidAt
    );
}
