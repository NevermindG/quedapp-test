package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;

import java.time.LocalDate;
import java.util.UUID;

public interface PayPlannedExpenseUseCase {

    ExpenseResult pay(
            UUID plannedExpenseId,
            UUID userId,
            LocalDate paidAt
    );
}
