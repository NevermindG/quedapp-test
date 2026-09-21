package com.quedav1.quedav1back.transaction.adapter.out.persistence.plannedexpense;

import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpense;

public final class PlannedExpensePersistenceMapper {

    private PlannedExpensePersistenceMapper() {
    }

    public static PlannedExpenseJpaEntity toEntity(
            PlannedExpense plannedExpense
    ) {
        return new PlannedExpenseJpaEntity(
                plannedExpense.getId(),
                plannedExpense.getUserId(),
                plannedExpense.getAmount(),
                plannedExpense.getCurrency(),
                plannedExpense.getDescription(),
                plannedExpense.getCategory(),
                plannedExpense.getDueDate(),
                plannedExpense.getStatus(),
                plannedExpense.getCreatedAt(),
                plannedExpense.getUpdatedAt()
        );
    }

    public static PlannedExpense toDomain(
            PlannedExpenseJpaEntity entity
    ) {
        return new PlannedExpense(
                entity.getId(),
                entity.getUserId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getDescription(),
                entity.getCategory(),
                entity.getDueDate(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
