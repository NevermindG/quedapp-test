package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.BudgetAlreadyExistsException;
import com.quedav1.quedav1back.transaction.application.exception.InvalidBudgetException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.BudgetResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreateBudgetCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreateBudgetUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.BudgetRepository;
import com.quedav1.quedav1back.transaction.domain.model.budget.Budget;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CreateBudgetService
        implements CreateBudgetUseCase {

    private final BudgetRepository budgetRepository;

    public CreateBudgetService(
            BudgetRepository budgetRepository
    ) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public BudgetResult create(
            UUID userId,
            CreateBudgetCommand command
    ) {

        validate(command);

        budgetRepository
                .findByUserIdAndCategoryAndYearAndMonth(
                        userId,
                        command.category(),
                        command.year(),
                        command.month()
                )
                .ifPresent(budget -> {
                    throw new BudgetAlreadyExistsException();
                });

        Instant now = Instant.now();

        Budget budget =
                new Budget(
                        UUID.randomUUID(),
                        userId,
                        command.category(),
                        command.amount(),
                        command.currency(),
                        command.year(),
                        command.month(),
                        now,
                        now
                );

        Budget savedBudget =
                budgetRepository.save(budget);

        return BudgetResult.from(savedBudget);
    }

    private void validate(
            CreateBudgetCommand command
    ) {

        if (
                command.amount() == null
                        || command.amount()
                        .compareTo(BigDecimal.ZERO) <= 0
        ) {
            throw new InvalidBudgetException(
                    "Budget amount must be greater than zero"
            );
        }

        if (
                command.month() < 1
                        || command.month() > 12
        ) {
            throw new InvalidBudgetException(
                    "Budget month must be between 1 and 12"
            );
        }

        if (command.category() == null) {
            throw new InvalidBudgetException(
                    "Budget category is required"
            );
        }

        if (command.currency() == null) {
            throw new InvalidBudgetException(
                    "Budget currency is required"
            );
        }
    }
}