package com.quedav1.quedav1back.transaction.adapter.out.persistence.saving;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SpringDataSavingsContributionRepository
        extends JpaRepository<SavingsContributionJpaEntity, UUID> {

    List<SavingsContributionJpaEntity>
    findBySavingsGoalIdAndUserId(
            UUID savingsGoalId,
            UUID userId
    );
    List<SavingsContributionJpaEntity> findByUserIdAndDateBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    );

    List<SavingsContributionJpaEntity>
    findBySavingsGoalIdAndUserIdOrderByDateDesc(
            UUID savingsGoalId,
            UUID userId
    );
}
