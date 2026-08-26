package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.FinancialOverviewResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetFinancialOverviewUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.*;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;
import com.quedav1.quedav1back.transaction.domain.model.financial.FinancialCalculation;
import com.quedav1.quedav1back.transaction.domain.model.financial.FinancialEngine;
import com.quedav1.quedav1back.transaction.domain.model.income.Income;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpense;
import com.quedav1.quedav1back.transaction.domain.model.saving.SavingsContribution;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class GetFinancialOverviewService
        implements GetFinancialOverviewUseCase {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final PlannedExpenseRepository plannedExpenseRepository;
    private final SavingsContributionRepository savingsContributionRepository;
    private final UserRepository userRepository;
    private final FinancialEngine financialEngine;


    public GetFinancialOverviewService(
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            PlannedExpenseRepository plannedExpenseRepository,
            SavingsContributionRepository savingsContributionRepository,
            UserRepository userRepository,
            FinancialEngine financialEngine
    ) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.plannedExpenseRepository = plannedExpenseRepository;
        this.savingsContributionRepository = savingsContributionRepository;
        this.userRepository = userRepository;
        this.financialEngine = financialEngine;
    }


    @Override
    public FinancialOverviewResult getOverview(UUID userId) {

        LocalDate today =
                LocalDate.now();


        /*
         * PERÍODO ACTUAL
         */
        YearMonth currentPeriod =
                YearMonth.from(today);

        LocalDate currentFrom =
                currentPeriod.atDay(1);

        LocalDate currentTo =
                currentPeriod.atEndOfMonth();


        /*
         * PERÍODO ANTERIOR
         */
        YearMonth previousPeriod =
                currentPeriod.minusMonths(1);

        LocalDate previousFrom =
                previousPeriod.atDay(1);


        /*
         * Queremos comparar períodos equivalentes.
         *
         * Ejemplo:
         *
         * hoy = 24 agosto
         *
         * actual:
         * 1 agosto → 24 agosto
         *
         * anterior:
         * 1 julio → 24 julio
         */
        int currentDay =
                today.getDayOfMonth();

        int previousComparisonDay =
                Math.min(
                        currentDay,
                        previousPeriod.lengthOfMonth()
                );

        LocalDate previousComparisonTo =
                previousPeriod.atDay(
                        previousComparisonDay
                );


        /*
         * INGRESOS DEL MES ACTUAL
         */
        List<Income> incomes =
                incomeRepository
                        .findByUserIdAndOccurredAtBetween(
                                userId,
                                currentFrom,
                                currentTo
                        );


        /*
         * GASTOS DEL MES ACTUAL
         *
         * Esto alimenta balance / committed.
         */
        List<Expense> expenses =
                expenseRepository
                        .findByUserIdAndOccurredAtBetween(
                                userId,
                                currentFrom,
                                currentTo
                        );


        /*
         * GASTOS ACTUALES HASTA HOY
         *
         * Se utilizan exclusivamente para
         * comparar el ritmo de gasto.
         */
        List<Expense> currentComparisonExpenses =
                expenseRepository
                        .findByUserIdAndOccurredAtBetween(
                                userId,
                                currentFrom,
                                today
                        );

        /*
         * GASTOS ACTUALES HASTA HOY POR CATEGORIA
         */
        Map<ExpenseCategory, BigDecimal> currentExpensesByCategory =
                currentComparisonExpenses.stream()
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


        /*
         * MISMO PERÍODO DEL MES ANTERIOR
         */
        List<Expense> previousComparisonExpenses =
                expenseRepository
                        .findByUserIdAndOccurredAtBetween(
                                userId,
                                previousFrom,
                                previousComparisonTo
                        );

        int previousExpenseCount =
                previousComparisonExpenses.size();

        /*
         * MISMO PERÍODO DEL MES ANTERIOR POR CATEGORIA
         */
        Map<ExpenseCategory, BigDecimal> previousExpensesByCategory =
                previousComparisonExpenses.stream()
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

        Map<ExpenseCategory, Long> previousExpenseCountByCategory =
                previousComparisonExpenses.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Expense::getCategory,
                                        Collectors.counting()
                                )
                        );


        /*
         * PLANNED EXPENSES PENDIENTES
         *
         * Solo desde hoy hasta fin del mes.
         */
        List<PlannedExpense> pendingPlannedExpenses =
                plannedExpenseRepository
                        .findPendingByUserIdAndDueDateBetween(
                                userId,
                                today,
                                currentTo
                        );


        /*
         * CONTRIBUCIONES DE AHORRO
         * REALIZADAS ESTE MES
         */
        List<SavingsContribution> savingsContributions =
                savingsContributionRepository
                        .findByUserIdAndDateBetween(
                                userId,
                                currentFrom,
                                currentTo
                        );


        /*
         * TOTAL INGRESOS
         */
        BigDecimal totalIncome =
                incomes.stream()
                        .map(Income::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        /*
         * TOTAL GASTOS
         */
        BigDecimal totalExpenses =
                expenses.stream()
                        .map(Expense::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );

        /*
        * GASTOS POR CATEGORIA
         */
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


        /*
         * TOTAL PLANNED PENDIENTE
         */
        BigDecimal totalPending =
                pendingPlannedExpenses.stream()
                        .map(PlannedExpense::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        /*
         * TOTAL AHORRADO ESTE MES
         */
        BigDecimal totalSavings =
                savingsContributions.stream()
                        .map(SavingsContribution::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        /*
         * GASTO ACTUAL PARA TREND
         */
        BigDecimal currentPeriodExpenses =
                currentComparisonExpenses.stream()
                        .map(Expense::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        /*
         * GASTO DEL MISMO PERÍODO
         * DEL MES ANTERIOR
         */
        BigDecimal previousPeriodExpenses =
                previousComparisonExpenses.stream()
                        .map(Expense::getAmount)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        /*
         * DÍAS RESTANTES
         *
         * Incluye hoy.
         */
        int remainingDays =
                currentPeriod.lengthOfMonth()
                        - today.getDayOfMonth()
                        + 1;


        /*
         * FINANCIAL ENGINE
         */
        FinancialCalculation calculation =
                financialEngine.calculate(
                        totalIncome,
                        totalExpenses,
                        totalPending,
                        totalSavings,
                        currentPeriodExpenses,
                        previousPeriodExpenses,
                        previousExpenseCount,
                        remainingDays,
                        expensesByCategory,
                        currentExpensesByCategory,
                        previousExpensesByCategory,
                        previousExpenseCountByCategory
                );


        /*
         * USER
         */
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(
                                UserNotFoundException::new
                        );


        return new FinancialOverviewResult(
                currentPeriod.getYear(),
                currentPeriod.getMonthValue(),

                totalIncome,
                totalExpenses,
                totalPending,
                totalSavings,

                calculation.spendingCommitment(),
                calculation.spendingCommitmentPercentage(),

                calculation.reservedSavings(),
                calculation.savingsRatePercentage(),

                calculation.balance(),
                calculation.availableToSpend(),
                calculation.dailyAvailable(),

                remainingDays,

                calculation.spendingTrendPercentage(),

                user.getCurrency(),
                calculation.status(),

                calculation.insights(),
                calculation.expenseCategoryBreakdown()
        );
    }
}