package com.quedav1.quedav1back.transaction.adapter.in.web.financial.plannedexpense;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdatePlannedExpenseRequest(
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        LocalDate dueDate
) {
}
