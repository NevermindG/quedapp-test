package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense.PlannedExpense;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense.PlannedExpenseStatus;

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
