package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.budget;

import com.quedav1.quedav1back.transaction.application.exception.BudgetNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budgets.DeleteBudgetUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.budget.BudgetRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.budgets.Budget;

import java.util.UUID;

public class DeleteBudgetService
        implements DeleteBudgetUseCase {

    private final BudgetRepository budgetRepository;

    public DeleteBudgetService(
            BudgetRepository budgetRepository
    ) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public void delete(
            UUID budgetId,
            UUID userId
    ) {

        Budget budget =
                budgetRepository
                        .findByIdAndUserId(
                                budgetId,
                                userId
                        )
                        .orElseThrow(
                                BudgetNotFoundException::new
                        );

        budgetRepository.delete(
                budget
        );
    }
}
