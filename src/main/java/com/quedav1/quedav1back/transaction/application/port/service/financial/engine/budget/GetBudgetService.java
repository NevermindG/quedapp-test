package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.budget;

import com.quedav1.quedav1back.transaction.application.exception.BudgetNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budget.BudgetResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budget.GetBudgetUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.budget.BudgetRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.budget.Budget;

import java.util.UUID;

public class GetBudgetService
        implements GetBudgetUseCase {

    private final BudgetRepository budgetRepository;

    public GetBudgetService(
            BudgetRepository budgetRepository
    ) {
        this.budgetRepository = budgetRepository;
    }

    @Override
    public BudgetResult getBudget(
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

        return BudgetResult.from(
                budget
        );
    }
}
