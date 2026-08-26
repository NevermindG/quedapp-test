package com.quedav1.quedav1back.transaction.adapter.out.persistence.saving;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataSavingsGoalRepository
        extends JpaRepository<SavingsGoalJpaEntity, UUID> {

    List<SavingsGoalJpaEntity> findByUserId(UUID userId);

    Optional<SavingsGoalJpaEntity> findByIdAndUserId(
            UUID id,
            UUID userId
    );
}
