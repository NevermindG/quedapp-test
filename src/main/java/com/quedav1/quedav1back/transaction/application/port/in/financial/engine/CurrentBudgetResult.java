package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.util.UUID;

public record CurrentBudgetResult(
        UUID id,
        ExpenseCategory category,
        BigDecimal budgetAmount,
        BigDecimal spentAmount,
        BigDecimal remainingAmount,
        BigDecimal usedPercentage,
        Currency currency,
        int year,
        int month
) {
}
