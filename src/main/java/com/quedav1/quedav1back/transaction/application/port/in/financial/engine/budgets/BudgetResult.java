package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budgets;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.financial.budgets.Budget;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;

import java.math.BigDecimal;
import java.util.UUID;

public record BudgetResult(
        UUID id,
        ExpenseCategory category,
        BigDecimal amount,
        Currency currency,
        int year,
        int month
) {

    public static BudgetResult from(Budget budget) {

        return new BudgetResult(
                budget.getId(),
                budget.getCategory(),
                budget.getAmount(),
                budget.getCurrency(),
                budget.getYear(),
                budget.getMonth()
        );
    }
}
