package com.quedav1.quedav1back.transaction.adapter.out.persistence.saving;

import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsContribution;

public final class SavingsContributionPersistenceMapper {

    private SavingsContributionPersistenceMapper() {}

    public static SavingsContributionJpaEntity toEntity(
            SavingsContribution contribution
    ) {
        return new SavingsContributionJpaEntity(
                contribution.getId(),
                contribution.getSavingsGoalId(),
                contribution.getUserId(),
                contribution.getAmount(),
                contribution.getDate(),
                contribution.getCreatedAt()
        );
    }

    public static SavingsContribution toDomain(
            SavingsContributionJpaEntity entity
    ) {
        return new SavingsContribution(
                entity.getId(),
                entity.getSavingsGoalId(),
                entity.getUserId(),
                entity.getAmount(),
                entity.getDate(),
                entity.getCreatedAt()
        );
    }
}
