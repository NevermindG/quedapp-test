package com.quedav1.quedav1back.transaction.adapter.out.persistence.budget;

import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataBudgetRepository
        extends JpaRepository<BudgetJpaEntity, UUID> {

    Optional<BudgetJpaEntity>
    findByUserIdAndCategoryAndYearAndMonth(
            UUID userId,
            ExpenseCategory category,
            int year,
            int month
    );

    List<BudgetJpaEntity> findByUserIdAndYearAndMonth(
            UUID userId,
            int year,
            int month
    );

    Optional<BudgetJpaEntity> findByIdAndUserId(
            UUID id,
            UUID userId
    );
}
