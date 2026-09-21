package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpense;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpenseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record PlannedExpenseResult(
        UUID id,
        UUID userId,
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        LocalDate dueDate,
        PlannedExpenseStatus status
) {

    public static PlannedExpenseResult from(
            PlannedExpense plannedExpense
    ) {
        return new PlannedExpenseResult(
                plannedExpense.getId(),
                plannedExpense.getUserId(),
                plannedExpense.getAmount(),
                plannedExpense.getCurrency(),
                plannedExpense.getDescription(),
                plannedExpense.getCategory(),
                plannedExpense.getDueDate(),
                plannedExpense.getStatus()
        );
    }
}
