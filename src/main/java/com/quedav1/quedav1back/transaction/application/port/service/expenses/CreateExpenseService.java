package com.quedav1.quedav1back.transaction.application.port.service.expenses;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.expenses.CreateExpenseCommand;
import com.quedav1.quedav1back.transaction.application.port.in.expenses.CreateExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.expenses.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.out.expenses.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserCurrencyValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.expenses.Expense;

import java.time.Instant;
import java.util.UUID;

public class CreateExpenseService implements CreateExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public CreateExpenseService(
            ExpenseRepository expenseRepository, UserRepository userRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExpenseResult create(CreateExpenseCommand command) {

        User user =
                userRepository
                        .findById(command.userId())
                        .orElseThrow(
                                UserNotFoundException::new
                        );

        UserCurrencyValidator.validate(
                user.getCurrency(),
                command.currency()
        );

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