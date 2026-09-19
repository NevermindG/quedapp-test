package com.quedav1.quedav1back.transaction.domain.model.financial;

import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FinancialEngine {

    private static final BigDecimal HEALTHY_THRESHOLD =
            new BigDecimal("0.70");

    private static final BigDecimal WARNING_THRESHOLD =
            new BigDecimal("0.90");

    private static final BigDecimal SPENDING_TREND_THRESHOLD =
            new BigDecimal("10.00");

    private static final BigDecimal CATEGORY_TREND_THRESHOLD =
            new BigDecimal("15.00");

    private static final int MIN_PREVIOUS_EXPENSES_FOR_TREND = 3;

    private static final long MIN_PREVIOUS_CATEGORY_EXPENSES_FOR_TREND = 2;


    public FinancialCalculation calculate(
            BigDecimal totalIncome,
            BigDecimal totalExpenses,
            BigDecimal pendingPlannedExpenses,
            BigDecimal savingsContributions,
            BigDecimal currentPeriodExpenses,
            BigDecimal previousPeriodExpenses,
            int previousExpenseCount,
            int remainingDays,
            Map<ExpenseCategory, BigDecimal> expensesByCategory,
            Map<ExpenseCategory, BigDecimal> currentExpensesByCategory,
            Map<ExpenseCategory, BigDecimal> previousExpensesByCategory,
            Map<ExpenseCategory, Long> previousExpenseCountByCategory
    ) {

        BigDecimal balance =
                totalIncome.subtract(totalExpenses);

        BigDecimal spendingCommitment =
                totalExpenses
                        .add(pendingPlannedExpenses);

        BigDecimal reservedSavings =
                savingsContributions;

        BigDecimal savingsRatePercentage =
                calculateSavingsRate(
                        totalIncome,
                        reservedSavings
                );

        BigDecimal availableToSpend =
                balance
                        .subtract(pendingPlannedExpenses)
                        .subtract(reservedSavings)
                        .max(BigDecimal.ZERO);

        BigDecimal dailyAvailable =
                calculateDailyAvailable(
                        availableToSpend,
                        remainingDays
                );

        BigDecimal spendingCommitmentPercentage =
                calculateCommittedPercentage(
                        totalIncome,
                        spendingCommitment
                );

        boolean hasEnoughPreviousData =
                previousExpenseCount >= MIN_PREVIOUS_EXPENSES_FOR_TREND;

        BigDecimal spendingTrendPercentage =
                hasEnoughPreviousData
                        ? calculateSpendingTrend(
                        currentPeriodExpenses,
                        previousPeriodExpenses
                )
                        : BigDecimal.ZERO;

        FinancialStatus status =
                calculateStatus(
                        totalIncome,
                        spendingCommitment
                );

        List<FinancialInsight> insights =
                calculateGeneralInsights(
                        totalIncome,
                        spendingCommitmentPercentage,
                        dailyAvailable,
                        spendingTrendPercentage,
                        hasEnoughPreviousData,
                        status
                );

        FinancialScore financialScore =
                calculateFinancialScore(
                        spendingCommitmentPercentage,
                        savingsRatePercentage,
                        spendingTrendPercentage,
                        hasEnoughPreviousData
                );

        /*
         * SOLO calculamos insights por categoría
         * si tenemos suficiente histórico global.
         *
         * Dentro del método también validamos
         * cuántos registros históricos existen
         * por cada categoría.
         */
        insights.addAll(
                calculateCategoryInsights(
                        currentExpensesByCategory,
                        previousExpensesByCategory,
                        previousExpenseCountByCategory
                )
        );

        List<ExpenseCategoryBreakdown> categoryBreakdown =
                calculateCategoryBreakdown(
                        totalExpenses,
                        expensesByCategory
                );

        insights.add(
                calculateSavingsInsight(
                        savingsRatePercentage
                )
        );

        return new FinancialCalculation(
                balance,
                spendingCommitment,
                spendingCommitmentPercentage,
                reservedSavings,
                savingsRatePercentage,
                availableToSpend,
                dailyAvailable,
                spendingTrendPercentage,
                status,
                financialScore,
                insights,
                categoryBreakdown
        );
    }


    private BigDecimal calculateDailyAvailable(
            BigDecimal availableToSpend,
            int remainingDays
    ) {

        if (remainingDays <= 0) {
            return BigDecimal.ZERO;
        }

        return availableToSpend.divide(
                BigDecimal.valueOf(remainingDays),
                2,
                RoundingMode.HALF_UP
        );
    }


    private BigDecimal calculateCommittedPercentage(
            BigDecimal totalIncome,
            BigDecimal committed
    ) {

        if (totalIncome.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        return committed
                .divide(
                        totalIncome,
                        4,
                        RoundingMode.HALF_UP
                )
                .multiply(new BigDecimal("100"))
                .setScale(
                        2,
                        RoundingMode.HALF_UP
                );
    }


    private BigDecimal calculateSpendingTrend(
            BigDecimal currentExpenses,
            BigDecimal previousExpenses
    ) {

        if (previousExpenses.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return currentExpenses
                .subtract(previousExpenses)
                .divide(
                        previousExpenses,
                        4,
                        RoundingMode.HALF_UP
                )
                .multiply(new BigDecimal("100"))
                .setScale(
                        2,
                        RoundingMode.HALF_UP
                );
    }


    private FinancialStatus calculateStatus(
            BigDecimal totalIncome,
            BigDecimal committed
    ) {

        if (totalIncome.compareTo(BigDecimal.ZERO) == 0) {

            if (committed.compareTo(BigDecimal.ZERO) > 0) {
                return FinancialStatus.OVERSPENDING;
            }

            return FinancialStatus.WARNING;
        }

        BigDecimal committedRatio =
                committed.divide(
                        totalIncome,
                        4,
                        RoundingMode.HALF_UP
                );

        if (committedRatio.compareTo(HEALTHY_THRESHOLD) < 0) {
            return FinancialStatus.HEALTHY;
        }

        if (committedRatio.compareTo(WARNING_THRESHOLD) <= 0) {
            return FinancialStatus.WARNING;
        }

        return FinancialStatus.OVERSPENDING;
    }


    private List<FinancialInsight> calculateGeneralInsights(
            BigDecimal totalIncome,
            BigDecimal committedPercentage,
            BigDecimal dailyAvailable,
            BigDecimal spendingTrendPercentage,
            boolean hasEnoughPreviousData,
            FinancialStatus status
    ) {

        List<FinancialInsight> insights =
                new ArrayList<>();

        /*
         * SIN INGRESOS
         */
        if (totalIncome.compareTo(BigDecimal.ZERO) == 0) {

            insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.NO_INCOME,
                            FinancialInsightSeverity.WARNING,
                            BigDecimal.ZERO,
                            null
                    )
            );

            return insights;
        }

        /*
         * ESTADO GENERAL
         */
        switch (status) {

            case HEALTHY -> insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.HEALTHY_COMMITMENT,
                            FinancialInsightSeverity.POSITIVE,
                            committedPercentage,
                            null
                    )
            );

            case WARNING -> insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.HIGH_COMMITMENT,
                            FinancialInsightSeverity.WARNING,
                            committedPercentage,
                            null
                    )
            );

            case OVERSPENDING -> insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.OVERSPENDING,
                            FinancialInsightSeverity.CRITICAL,
                            committedPercentage,
                            null
                    )
            );
        }

        /*
         * DISPONIBILIDAD DIARIA BAJA
         */
        if (
                dailyAvailable.compareTo(
                        new BigDecimal("50.00")
                ) < 0
        ) {

            insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.LOW_DAILY_AVAILABLE,
                            FinancialInsightSeverity.WARNING,
                            dailyAvailable,
                            null
                    )
            );
        }

        /*
         * NO HAY SUFICIENTE HISTÓRICO
         */
        if (!hasEnoughPreviousData) {

            insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.NO_PREVIOUS_SPENDING_DATA,
                            FinancialInsightSeverity.INFO,
                            BigDecimal.ZERO,
                            null
                    )
            );

            return insights;
        }

        /*
         * TENDENCIA GLOBAL
         */
        if (
                spendingTrendPercentage.compareTo(
                        SPENDING_TREND_THRESHOLD
                ) > 0
        ) {

            insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.SPENDING_UP,
                            FinancialInsightSeverity.WARNING,
                            spendingTrendPercentage,
                            null
                    )
            );

        } else if (
                spendingTrendPercentage.compareTo(
                        SPENDING_TREND_THRESHOLD.negate()
                ) < 0
        ) {

            insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.SPENDING_DOWN,
                            FinancialInsightSeverity.POSITIVE,
                            spendingTrendPercentage.abs(),
                            null
                    )
            );

        } else {

            insights.add(
                    new FinancialInsight(
                            FinancialInsightCode.SPENDING_STABLE,
                            FinancialInsightSeverity.INFO,
                            spendingTrendPercentage.abs(),
                            null
                    )
            );
        }

        return insights;
    }


    private List<FinancialInsight> calculateCategoryInsights(
            Map<ExpenseCategory, BigDecimal> currentExpensesByCategory,
            Map<ExpenseCategory, BigDecimal> previousExpensesByCategory,
            Map<ExpenseCategory, Long> previousExpenseCountByCategory
    ) {

        List<FinancialInsight> insights =
                new ArrayList<>();

        for (
                Map.Entry<ExpenseCategory, BigDecimal> entry
                : currentExpensesByCategory.entrySet()
        ) {

            ExpenseCategory category =
                    entry.getKey();

            BigDecimal currentAmount =
                    entry.getValue();

            BigDecimal previousAmount =
                    previousExpensesByCategory.getOrDefault(
                            category,
                            BigDecimal.ZERO
                    );

            long previousCount =
                    previousExpenseCountByCategory.getOrDefault(
                            category,
                            0L
                    );

            /*
             * Si solamente hubo 0 o 1 movimientos
             * históricos de esa categoría,
             * no sacamos conclusiones.
             */
            if (
                    previousCount
                            < MIN_PREVIOUS_CATEGORY_EXPENSES_FOR_TREND
            ) {
                continue;
            }

            if (previousAmount.compareTo(BigDecimal.ZERO) <= 0) {
                continue;
            }

            BigDecimal variationPercentage =
                    currentAmount
                            .subtract(previousAmount)
                            .divide(
                                    previousAmount,
                                    4,
                                    RoundingMode.HALF_UP
                            )
                            .multiply(new BigDecimal("100"))
                            .setScale(
                                    2,
                                    RoundingMode.HALF_UP
                            );

            if (
                    variationPercentage.compareTo(
                            CATEGORY_TREND_THRESHOLD
                    ) > 0
            ) {

                insights.add(
                        new FinancialInsight(
                                FinancialInsightCode.CATEGORY_SPENDING_UP,
                                FinancialInsightSeverity.WARNING,
                                variationPercentage,
                                category
                        )
                );

            } else if (
                    variationPercentage.compareTo(
                            CATEGORY_TREND_THRESHOLD.negate()
                    ) < 0
            ) {

                insights.add(
                        new FinancialInsight(
                                FinancialInsightCode.CATEGORY_SPENDING_DOWN,
                                FinancialInsightSeverity.POSITIVE,
                                variationPercentage.abs(),
                                category
                        )
                );
            }
        }

        return insights;
    }


    private List<ExpenseCategoryBreakdown> calculateCategoryBreakdown(
            BigDecimal totalExpenses,
            Map<ExpenseCategory, BigDecimal> expensesByCategory
    ) {

        if (totalExpenses.compareTo(BigDecimal.ZERO) <= 0) {
            return List.of();
        }

        return expensesByCategory
                .entrySet()
                .stream()
                .map(entry -> {

                    BigDecimal percentage =
                            entry.getValue()
                                    .divide(
                                            totalExpenses,
                                            4,
                                            RoundingMode.HALF_UP
                                    )
                                    .multiply(
                                            new BigDecimal("100")
                                    )
                                    .setScale(
                                            2,
                                            RoundingMode.HALF_UP
                                    );

                    return new ExpenseCategoryBreakdown(
                            entry.getKey(),
                            entry.getValue(),
                            percentage
                    );
                })
                .sorted(
                        Comparator.comparing(
                                ExpenseCategoryBreakdown::amount
                        ).reversed()
                )
                .toList();
    }

    private BigDecimal calculateSavingsRate(
            BigDecimal totalIncome,
            BigDecimal reservedSavings
    ) {

        if (totalIncome.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        return reservedSavings
                .divide(
                        totalIncome,
                        4,
                        RoundingMode.HALF_UP
                )
                .multiply(new BigDecimal("100"))
                .setScale(
                        2,
                        RoundingMode.HALF_UP
                );
    }

    private FinancialInsight calculateSavingsInsight(
            BigDecimal savingsRatePercentage
    ) {

        if (savingsRatePercentage.compareTo(BigDecimal.ZERO) == 0) {

            return new FinancialInsight(
                    FinancialInsightCode.NO_SAVINGS,
                    FinancialInsightSeverity.INFO,
                    BigDecimal.ZERO,
                    null
            );
        }

        if (
                savingsRatePercentage.compareTo(
                        new BigDecimal("10.00")
                ) < 0
        ) {

            return new FinancialInsight(
                    FinancialInsightCode.LOW_SAVINGS,
                    FinancialInsightSeverity.INFO,
                    savingsRatePercentage,
                    null
            );
        }

        return new FinancialInsight(
                FinancialInsightCode.HEALTHY_SAVINGS,
                FinancialInsightSeverity.POSITIVE,
                savingsRatePercentage,
                null
        );
    }

    private FinancialScore calculateFinancialScore(
            BigDecimal spendingCommitmentPercentage,
            BigDecimal savingsRatePercentage,
            BigDecimal spendingTrendPercentage,
            boolean hasEnoughPreviousData
    ) {

        int commitmentPoints =
                calculateCommitmentScore(
                        spendingCommitmentPercentage
                );

        int savingsPoints =
                calculateSavingsScore(
                        savingsRatePercentage
                );

        int trendPoints = 0;

        int maximumPoints = 90;

        if (hasEnoughPreviousData) {

            trendPoints =
                    calculateSpendingTrendScore(
                            spendingTrendPercentage
                    );

            maximumPoints += 10;
        }

        int earnedPoints =
                commitmentPoints
                        + savingsPoints
                        + trendPoints;

        int normalizedScore =
                BigDecimal.valueOf(earnedPoints)
                        .divide(
                                BigDecimal.valueOf(maximumPoints),
                                4,
                                RoundingMode.HALF_UP
                        )
                        .multiply(
                                BigDecimal.valueOf(100)
                        )
                        .setScale(
                                0,
                                RoundingMode.HALF_UP
                        )
                        .intValue();

        FinancialScoreLevel level =
                calculateFinancialScoreLevel(
                        normalizedScore
                );

        return new FinancialScore(
                normalizedScore,
                level,
                commitmentPoints,
                savingsPoints,
                trendPoints,
                hasEnoughPreviousData
        );
    }

    private int calculateCommitmentScore(
            BigDecimal spendingCommitmentPercentage
    ) {

        if (
                spendingCommitmentPercentage.compareTo(
                        new BigDecimal("50")
                ) <= 0
        ) {
            return 60;
        }

        if (
                spendingCommitmentPercentage.compareTo(
                        new BigDecimal("70")
                ) <= 0
        ) {
            return 50;
        }

        if (
                spendingCommitmentPercentage.compareTo(
                        new BigDecimal("90")
                ) <= 0
        ) {
            return 30;
        }

        if (
                spendingCommitmentPercentage.compareTo(
                        new BigDecimal("100")
                ) <= 0
        ) {
            return 10;
        }

        return 0;
    }

    private int calculateSavingsScore(
            BigDecimal savingsRatePercentage
    ) {

        if (
                savingsRatePercentage.compareTo(
                        new BigDecimal("20")
                ) >= 0
        ) {
            return 30;
        }

        if (
                savingsRatePercentage.compareTo(
                        new BigDecimal("10")
                ) >= 0
        ) {
            return 20;
        }

        if (
                savingsRatePercentage.compareTo(
                        BigDecimal.ZERO
                ) > 0
        ) {
            return 10;
        }

        return 0;
    }

    private int calculateSpendingTrendScore(
            BigDecimal spendingTrendPercentage
    ) {

        /*
         * Gastar 10% o más menos que
         * el período anterior.
         */
        if (
                spendingTrendPercentage.compareTo(
                        new BigDecimal("-10")
                ) <= 0
        ) {
            return 10;
        }

        /*
         * Variación entre -10% y +10%.
         * Consideramos comportamiento estable.
         */
        if (
                spendingTrendPercentage.compareTo(
                        new BigDecimal("10")
                ) <= 0
        ) {
            return 8;
        }

        /*
         * Incremento moderado.
         */
        if (
                spendingTrendPercentage.compareTo(
                        new BigDecimal("25")
                ) <= 0
        ) {
            return 4;
        }

        /*
         * Incremento >25%
         */
        return 0;
    }

    private FinancialScoreLevel calculateFinancialScoreLevel(
            int score
    ) {

        if (score >= 85) {
            return FinancialScoreLevel.EXCELLENT;
        }

        if (score >= 70) {
            return FinancialScoreLevel.GOOD;
        }

        if (score >= 50) {
            return FinancialScoreLevel.CAUTION;
        }

        return FinancialScoreLevel.CRITICAL;
    }
}