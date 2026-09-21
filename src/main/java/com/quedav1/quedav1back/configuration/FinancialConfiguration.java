package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetFinancialOverviewUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetFinancialSummaryUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.*;
import com.quedav1.quedav1back.transaction.application.port.out.expenses.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.budget.BudgetRepository;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsContributionRepository;
import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.GetFinancialOverviewService;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.GetFinancialSummaryService;
import com.quedav1.quedav1back.transaction.domain.model.financial.FinancialEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FinancialConfiguration {

    @Bean
    public GetFinancialSummaryUseCase getFinancialSummaryUseCase(
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            UserRepository userRepository
    ) {
        return new GetFinancialSummaryService(
                incomeRepository,
                expenseRepository,
                userRepository
        );
    }

    @Bean
    public GetFinancialOverviewUseCase getFinancialOverviewUseCase(
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            PlannedExpenseRepository plannedExpenseRepository,
            SavingsContributionRepository savingsContributionRepository,
            UserRepository userRepository,
            FinancialEngine financialEngine,
            BudgetRepository budgetRepository
    ) {

        return new GetFinancialOverviewService(
                incomeRepository,
                expenseRepository,
                plannedExpenseRepository,
                savingsContributionRepository,
                userRepository,
                financialEngine,
                budgetRepository
        );
    }

    @Bean
    public FinancialEngine financialEngine() {
        return new FinancialEngine();
    }
}

/*
{
    "year": 2026,
    "month": 8,
    "totalIncome": 5800.00,
    "totalExpenses": 2035.40,
    "pendingPlannedExpenses": 0,
    "balance": 3764.60,
    "availableToSpend": 3764.60,
    "dailyAvailable": 418.29,
    "remainingDays": 9,
    "currency": "PEN"
}
{
    "year": 2026,
    "month": 8,
    "totalIncome": 5800.00,
    "totalExpenses": 2035.40,
    "pendingPlannedExpenses": 1500.00,
    "balance": 3764.60,
    "availableToSpend": 2264.60,
    "dailyAvailable": 251.62,
    "remainingDays": 9,
    "currency": "PEN"
}
 */
