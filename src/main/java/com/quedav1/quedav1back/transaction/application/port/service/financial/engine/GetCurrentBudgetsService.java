package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CurrentBudgetResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetCurrentBudgetsUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.BudgetRepository;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.budget.Budget;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class GetCurrentBudgetsService
        implements GetCurrentBudgetsUseCase {

    private final BudgetRepository budgetRepository;
    private final ExpenseRepository expenseRepository;

    public GetCurrentBudgetsService(
            BudgetRepository budgetRepository,
            ExpenseRepository expenseRepository
    ) {
        this.budgetRepository = budgetRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<CurrentBudgetResult> getCurrentBudgets(
            UUID userId
    ) {

        LocalDate today =
                LocalDate.now();

        YearMonth currentPeriod =
                YearMonth.from(today);

        LocalDate from =
                currentPeriod.atDay(1);

        /*
         * Aquí usamos "today" y no fin de mes.
         *
         * Queremos saber cuánto realmente se ha
         * gastado hasta el día actual.
         */
        LocalDate to =
                today;

        List<Budget> budgets =
                budgetRepository
                        .findByUserIdAndYearAndMonth(
                                userId,
                                currentPeriod.getYear(),
                                currentPeriod.getMonthValue()
                        );

        List<Expense> expenses =
                expenseRepository
                        .findByUserIdAndOccurredAtBetween(
                                userId,
                                from,
                                to
                        );

        Map<ExpenseCategory, BigDecimal> expensesByCategory =
                expenses.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Expense::getCategory,
                                        Collectors.reducing(
                                                BigDecimal.ZERO,
                                                Expense::getAmount,
                                                BigDecimal::add
                                        )
                                )
                        );

        return budgets.stream()
                .map(budget -> {

                    BigDecimal spentAmount =
                            expensesByCategory.getOrDefault(
                                    budget.getCategory(),
                                    BigDecimal.ZERO
                            );

                    BigDecimal remainingAmount =
                            budget.getAmount()
                                    .subtract(spentAmount);

                    BigDecimal usedPercentage =
                            calculateUsedPercentage(
                                    budget.getAmount(),
                                    spentAmount
                            );

                    return new CurrentBudgetResult(
                            budget.getId(),
                            budget.getCategory(),
                            budget.getAmount(),
                            spentAmount,
                            remainingAmount,
                            usedPercentage,
                            budget.getCurrency(),
                            budget.getYear(),
                            budget.getMonth()
                    );
                })
                .sorted(
                        Comparator.comparing(
                                result ->
                                        result.category().name()
                        )
                )
                .toList();
    }

    private BigDecimal calculateUsedPercentage(
            BigDecimal budgetAmount,
            BigDecimal spentAmount
    ) {

        if (
                budgetAmount.compareTo(
                        BigDecimal.ZERO
                ) <= 0
        ) {
            return BigDecimal.ZERO;
        }

        return spentAmount
                .divide(
                        budgetAmount,
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
    }
}
