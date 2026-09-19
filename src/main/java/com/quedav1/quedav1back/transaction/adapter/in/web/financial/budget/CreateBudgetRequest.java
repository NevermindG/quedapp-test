package com.quedav1.quedav1back.transaction.adapter.in.web.financial.budget;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;

public record CreateBudgetRequest(
        ExpenseCategory category,
        BigDecimal amount,
        Currency currency,
        int year,
        int month
) {
}
