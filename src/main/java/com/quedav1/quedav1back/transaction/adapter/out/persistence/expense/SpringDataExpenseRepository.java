package com.quedav1.quedav1back.transaction.adapter.out.persistence.expense;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataExpenseRepository
        extends JpaRepository<ExpenseJpaEntity, UUID> {
    List<ExpenseJpaEntity> findByUserId(UUID userId);
    Optional<ExpenseJpaEntity> findByIdAndUserId(
            UUID id,
            UUID userId
    );
    List<ExpenseJpaEntity> findByUserIdAndOccurredAtBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    );
}
