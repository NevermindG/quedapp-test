package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;

public record CreateBudgetCommand(
        ExpenseCategory category,
        BigDecimal amount,
        Currency currency,
        int year,
        int month
) {
}
