package com.quedav1.quedav1back.transaction.adapter.out.persistence.income;

import com.quedav1.quedav1back.transaction.domain.model.income.Income;

public final class IncomePersistenceMapper {

    private IncomePersistenceMapper() {
    }

    public static IncomeJpaEntity toEntity(Income income) {

        return new IncomeJpaEntity(
                income.getId(),
                income.getUserId(),
                income.getAmount(),
                income.getCurrency(),
                income.getDescription(),
                income.getCategory(),
                income.getOccurredAt(),
                income.getCreatedAt(),
                income.getUpdatedAt()
        );
    }

    public static Income toDomain(IncomeJpaEntity entity) {

        return new Income(
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
