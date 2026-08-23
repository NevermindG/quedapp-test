package com.quedav1.quedav1back.transaction.adapter.out.persistence.income;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataIncomeRepository
        extends JpaRepository<IncomeJpaEntity, UUID> {

    List<IncomeJpaEntity> findByUserId(UUID userId);

    Optional<IncomeJpaEntity> findByIdAndUserId(
            UUID incomeId,
            UUID userId
    );
}
