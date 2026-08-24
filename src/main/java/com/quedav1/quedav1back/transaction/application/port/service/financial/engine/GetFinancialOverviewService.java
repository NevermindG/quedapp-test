package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.FinancialOverviewResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetFinancialOverviewUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.IncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.out.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;
import com.quedav1.quedav1back.transaction.domain.model.income.Income;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpense;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public class GetFinancialOverviewService
        implements GetFinancialOverviewUseCase {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final PlannedExpenseRepository plannedExpenseRepository;
    private final UserRepository userRepository;

    public GetFinancialOverviewService(
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            PlannedExpenseRepository plannedExpenseRepository,
            UserRepository userRepository
    ) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.plannedExpenseRepository = plannedExpenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FinancialOverviewResult getOverview(UUID userId) {

        LocalDate today = LocalDate.now();

        YearMonth period = YearMonth.from(today);

        LocalDate from = period.atDay(1);
        LocalDate to = period.atEndOfMonth();

        List<Income> incomes =
                incomeRepository.findByUserIdAndOccurredAtBetween(
                        userId,
                        from,
                        to
                );

        List<Expense> expenses =
                expenseRepository.findByUserIdAndOccurredAtBetween(
                        userId,
                        from,
                        to
                );

        List<PlannedExpense> pendingPlannedExpenses =
                plannedExpenseRepository.findPendingByUserIdAndDueDateBetween(
                        userId,
                        today,
                        to
                );

        BigDecimal totalIncome = incomes.stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalPending = pendingPlannedExpenses.stream()
                .map(PlannedExpense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance =
                totalIncome.subtract(totalExpenses);

        BigDecimal availableToSpend =
                balance
                        .subtract(totalPending)
                        .max(BigDecimal.ZERO);

        int remainingDays =
                period.lengthOfMonth()
                        - today.getDayOfMonth()
                        + 1;

        BigDecimal dailyAvailable =
                remainingDays > 0
                        ? availableToSpend.divide(
                        BigDecimal.valueOf(remainingDays),
                        2,
                        RoundingMode.HALF_UP
                )
                        : BigDecimal.ZERO;

        User user = userRepository
                .findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return new FinancialOverviewResult(
                period.getYear(),
                period.getMonthValue(),
                totalIncome,
                totalExpenses,
                totalPending,
                balance,
                availableToSpend,
                dailyAvailable,
                remainingDays,
                user.getCurrency()
        );
    }
}
