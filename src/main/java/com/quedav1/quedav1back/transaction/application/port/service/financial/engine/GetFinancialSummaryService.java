package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.FinancialSummaryResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetFinancialSummaryUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.expenses.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.expenses.Expense;
import com.quedav1.quedav1back.transaction.domain.model.incomes.Income;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

public class GetFinancialSummaryService
        implements GetFinancialSummaryUseCase {

    private final IncomeRepository incomeRepository;
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public GetFinancialSummaryService(
            IncomeRepository incomeRepository,
            ExpenseRepository expenseRepository,
            UserRepository userRepository
    ) {
        this.incomeRepository = incomeRepository;
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public FinancialSummaryResult getSummary(
            UUID userId,
            int year,
            int month
    ) {

        YearMonth period = YearMonth.of(year, month);

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

        BigDecimal totalIncome = incomes.stream()
                .map(Income::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalExpenses = expenses.stream()
                .map(Expense::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance =
                totalIncome.subtract(totalExpenses);

        User user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        return new FinancialSummaryResult(
                year,
                month,
                totalIncome,
                totalExpenses,
                balance,
                user.getCurrency()
        );
    }
}
