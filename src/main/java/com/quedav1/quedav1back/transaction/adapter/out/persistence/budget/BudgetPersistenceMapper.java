package com.quedav1.quedav1back.transaction.adapter.out.persistence.budget;

import com.quedav1.quedav1back.transaction.domain.model.budget.Budget;

public class BudgetPersistenceMapper {

    public BudgetJpaEntity toEntity(
            Budget budget
    ) {

        return new BudgetJpaEntity(
                budget.getId(),
                budget.getUserId(),
                budget.getCategory(),
                budget.getAmount(),
                budget.getCurrency(),
                budget.getYear(),
                budget.getMonth(),
                budget.getCreatedAt(),
                budget.getUpdatedAt()
        );
    }

    public Budget toDomain(
            BudgetJpaEntity entity
    ) {

        return new Budget(
                entity.getId(),
                entity.getUserId(),
                entity.getCategory(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getYear(),
                entity.getMonth(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
