package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

import java.math.BigDecimal;

public record FinancialOverviewResult(
        int year,
        int month,
        BigDecimal totalIncome,
        BigDecimal totalExpenses,
        BigDecimal pendingPlannedExpenses,
        BigDecimal balance,
        BigDecimal availableToSpend,
        BigDecimal dailyAvailable,
        int remainingDays,
        Currency currency
) {
}