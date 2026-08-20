package com.quedav1.quedav1back.transaction.adapter.out.persistence.expense;

import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;

public final class ExpensePersistenceMapper {

    private ExpensePersistenceMapper() {
    }

    public static ExpenseJpaEntity toEntity(Expense expense) {

        return new ExpenseJpaEntity(
                expense.getId(),
                expense.getUserId(),
                expense.getAmount(),
                expense.getCurrency(),
                expense.getDescription(),
                expense.getCategory(),
                expense.getOccurredAt(),
                expense.getCreatedAt(),
                expense.getUpdatedAt()
        );
    }

    public static Expense toDomain(ExpenseJpaEntity entity) {

        return new Expense(
                entity.getId(),
                entity.getUserId(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getDescription(),
                entity.getCategory(),
                entity.getOccurredAt(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}