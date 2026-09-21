package com.quedav1.quedav1back.transaction.application.port.out.financial.engine.budget;

import com.quedav1.quedav1back.transaction.domain.model.financial.budget.Budget;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository {

    Budget save(Budget budget);

    Optional<Budget> findByUserIdAndCategoryAndYearAndMonth(
            UUID userId,
            ExpenseCategory category,
            int year,
            int month
    );

    List<Budget> findByUserIdAndYearAndMonth(
            UUID userId,
            int year,
            int month
    );

    Optional<Budget> findByIdAndUserId(
            UUID id,
            UUID userId
    );

    void delete(Budget budget);
}
