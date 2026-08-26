package com.quedav1.quedav1back.transaction.domain.model.financial;

import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;

public record ExpenseCategoryBreakdown(
        ExpenseCategory category,
        BigDecimal amount,
        BigDecimal percentage
) {
}
