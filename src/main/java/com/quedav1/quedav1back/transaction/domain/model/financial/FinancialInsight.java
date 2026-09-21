package com.quedav1.quedav1back.transaction.domain.model.financial;

import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;

import java.math.BigDecimal;

public record FinancialInsight(
        FinancialInsightCode code,
        FinancialInsightSeverity severity,
        BigDecimal value,
        ExpenseCategory category
) {
}
