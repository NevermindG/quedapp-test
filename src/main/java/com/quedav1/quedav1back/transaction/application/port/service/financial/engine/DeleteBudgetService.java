package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.BudgetNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.DeleteBudgetUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.BudgetRepository;
import com.quedav1.quedav1back.transaction.domain.model.budget.Budget;

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
