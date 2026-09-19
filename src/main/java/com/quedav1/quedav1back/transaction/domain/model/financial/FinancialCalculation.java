package com.quedav1.quedav1back.transaction.domain.model.financial;

import java.math.BigDecimal;
import java.util.List;

public record FinancialCalculation(
        BigDecimal balance,
        BigDecimal spendingCommitment,
        BigDecimal spendingCommitmentPercentage,
        BigDecimal reservedSavings,
        BigDecimal savingsRatePercentage,
        BigDecimal availableToSpend,
        BigDecimal dailyAvailable,
        BigDecimal spendingTrendPercentage,
        FinancialStatus status,
        FinancialScore financialScore,
        List<FinancialInsight> insights,
        List<ExpenseCategoryBreakdown> expenseCategoryBreakdown
) {
}