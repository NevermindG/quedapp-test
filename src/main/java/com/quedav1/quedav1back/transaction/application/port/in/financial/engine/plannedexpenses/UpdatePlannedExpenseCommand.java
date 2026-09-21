package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdatePlannedExpenseCommand(
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        LocalDate dueDate
) {
}
