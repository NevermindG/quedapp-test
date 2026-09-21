package com.quedav1.quedav1back.transaction.adapter.out.persistence.saving;

import com.quedav1.quedav1back.transaction.domain.model.financial.saving.SavingsGoal;

public final class SavingsGoalPersistenceMapper {

    private SavingsGoalPersistenceMapper() {
    }

    public static SavingsGoalJpaEntity toEntity(
            SavingsGoal savingsGoal
    ) {
        return new SavingsGoalJpaEntity(
                savingsGoal.getId(),
                savingsGoal.getUserId(),
                savingsGoal.getName(),
                savingsGoal.getTargetAmount(),
                savingsGoal.getCurrentAmount(),
                savingsGoal.getCurrency(),
                savingsGoal.getTargetDate(),
                savingsGoal.getStatus(),
                savingsGoal.getCreatedAt(),
                savingsGoal.getUpdatedAt()
        );
    }

    public static SavingsGoal toDomain(
            SavingsGoalJpaEntity entity
    ) {
        return new SavingsGoal(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getTargetAmount(),
                entity.getCurrentAmount(),
                entity.getCurrency(),
                entity.getTargetDate(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
