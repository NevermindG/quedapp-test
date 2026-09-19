package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.BudgetNotFoundException;
import com.quedav1.quedav1back.transaction.application.exception.InvalidBudgetException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.BudgetResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.UpdateBudgetCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.UpdateBudgetUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.BudgetRepository;
import com.quedav1.quedav1back.transaction.domain.model.budget.Budget;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdateBudgetService
        implements UpdateBudgetUseCase {

    private final BudgetRepository budgetRepository;

    public UpdateBudgetService(
            BudgetRepository budgetRepository
    ) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public BudgetResult update(
            UUID budgetId,
            UUID userId,
            UpdateBudgetCommand command
    ) {

        validate(command);

        Budget budget =
                budgetRepository
                        .findByIdAndUserId(
                                budgetId,
                                userId
                        )
                        .orElseThrow(
                                BudgetNotFoundException::new
                        );

        Budget updatedBudget =
                budget.updateAmount(
                        command.amount()
                );

        Budget savedBudget =
                budgetRepository.save(
                        updatedBudget
                );

        return BudgetResult.from(
                savedBudget
        );
    }

    private void validate(
            UpdateBudgetCommand command
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
    }
}
