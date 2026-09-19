package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.financial.*;

import java.math.BigDecimal;
import java.util.List;

public record FinancialOverviewResult(
        int year,
        int month,

        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal pendingPlannedExpenses,
        BigDecimal savingsContributions,

        BigDecimal spendingCommitment,
        BigDecimal spendingCommitmentPercentage,

        BigDecimal reservedSavings,
        BigDecimal savingsRatePercentage,

        BigDecimal balance,
        BigDecimal availableToSpend,
        BigDecimal dailyAvailable,

        int remainingDays,

        BigDecimal spendingTrendPercentage,

        Currency currency,
        FinancialStatus status,

        FinancialScore financialScore,

        List<FinancialInsight> insights,
        List<ExpenseCategoryBreakdown> expenseCategoryBreakdown
) {
}