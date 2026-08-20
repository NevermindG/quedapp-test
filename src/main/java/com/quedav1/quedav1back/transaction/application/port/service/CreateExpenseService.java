package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.CreateExpenseCommand;
import com.quedav1.quedav1back.transaction.application.port.in.CreateExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;

import java.time.Instant;
import java.util.UUID;

public class CreateExpenseService implements CreateExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public CreateExpenseService(
            ExpenseRepository expenseRepository
    ) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResult create(CreateExpenseCommand command) {

        Instant now = Instant.now();

        Expense expense = new Expense(
                UUID.randomUUID(),
                command.userId(),
                command.amount(),
                command.currency(),
                command.description(),
                command.category(),
                command.occurredAt(),
                now,
                now
        );

        Expense savedExpense = expenseRepository.save(expense);

        return new ExpenseResult(
                savedExpense.getId(),
                savedExpense.getUserId(),
                savedExpense.getAmount(),
                savedExpense.getCurrency(),
                savedExpense.getDescription(),
                savedExpense.getCategory(),
                savedExpense.getOccurredAt()
        );
    }
}