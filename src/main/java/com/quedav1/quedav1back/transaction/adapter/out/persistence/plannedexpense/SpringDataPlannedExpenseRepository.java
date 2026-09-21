package com.quedav1.quedav1back.transaction.adapter.out.persistence.plannedexpense;

import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense.PlannedExpenseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPlannedExpenseRepository
        extends JpaRepository<PlannedExpenseJpaEntity, UUID> {

    List<PlannedExpenseJpaEntity> findByUserId(UUID userId);

    Optional<PlannedExpenseJpaEntity> findByIdAndUserId(
            UUID id,
            UUID userId
    );

    List<PlannedExpenseJpaEntity>
    findByUserIdAndStatusAndDueDateBetween(
            UUID userId,
            PlannedExpenseStatus status,
            LocalDate from,
            LocalDate to
    );
}
