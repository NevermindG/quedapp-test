package com.quedav1.quedav1back.transaction.adapter.out.persistence.saving;

import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsContributionRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsContribution;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class SavingsContributionPersistenceAdapter
        implements SavingsContributionRepository {

    private final SpringDataSavingsContributionRepository repository;

    public SavingsContributionPersistenceAdapter(
            SpringDataSavingsContributionRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public SavingsContribution save(
            SavingsContribution contribution
    ) {

        SavingsContributionJpaEntity saved =
                repository.save(
                        SavingsContributionPersistenceMapper
                                .toEntity(contribution)
                );

        return SavingsContributionPersistenceMapper
                .toDomain(saved);
    }

    @Override
    public List<SavingsContribution> findBySavingsGoalIdAndUserId(
            UUID savingsGoalId,
            UUID userId
    ) {

        return repository
                .findBySavingsGoalIdAndUserIdOrderByDateDesc(
                        savingsGoalId,
                        userId
                )
                .stream()
                .map(SavingsContributionPersistenceMapper::toDomain)
                .toList();
    }
    @Override
    public List<SavingsContribution> findByUserIdAndDateBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    ) {
        return repository
                .findByUserIdAndDateBetween(userId, from, to)
                .stream()
                .map(SavingsContributionPersistenceMapper::toDomain)
                .toList();
    }
}
