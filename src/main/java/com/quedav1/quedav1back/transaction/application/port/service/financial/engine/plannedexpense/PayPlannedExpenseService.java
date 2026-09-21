package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.application.exception.PlannedExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.expenses.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.PayPlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.expenses.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.expenses.Expense;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpense;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class PayPlannedExpenseService
        implements PayPlannedExpenseUseCase {

    private final PlannedExpenseRepository plannedExpenseRepository;
    private final ExpenseRepository expenseRepository;

    public PayPlannedExpenseService(
            PlannedExpenseRepository plannedExpenseRepository,
            ExpenseRepository expenseRepository
    ) {
        this.plannedExpenseRepository = plannedExpenseRepository;
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResult pay(
            UUID plannedExpenseId,
            UUID userId,
            LocalDate paidAt
    ) {

        PlannedExpense plannedExpense =
                plannedExpenseRepository
                        .findByIdAndUserId(
                                plannedExpenseId,
                                userId
                        )
                        .orElseThrow(
                                PlannedExpenseNotFoundException::new
                        );

        PlannedExpense paidPlannedExpense =
                plannedExpense.markAsPaid();

        Instant now = Instant.now();

        Expense expense = new Expense(
                UUID.randomUUID(),
                userId,
                plannedExpense.getAmount(),
                plannedExpense.getCurrency(),
                plannedExpense.getDescription(),
                plannedExpense.getCategory(),
                paidAt,
                now,
                now
        );

        Expense savedExpense =
                expenseRepository.save(expense);

        plannedExpenseRepository.save(
                paidPlannedExpense
        );

        return ExpenseResult.from(savedExpense);
    }
}
