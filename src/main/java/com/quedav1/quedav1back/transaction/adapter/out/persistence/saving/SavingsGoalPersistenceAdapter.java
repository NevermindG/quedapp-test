package com.quedav1.quedav1back.transaction.adapter.out.persistence.saving;

import com.quedav1.quedav1back.transaction.application.port.out.SavingsGoalRepository;
import com.quedav1.quedav1back.transaction.domain.model.saving.SavingsGoal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class SavingsGoalPersistenceAdapter
        implements SavingsGoalRepository {

    private final SpringDataSavingsGoalRepository repository;

    public SavingsGoalPersistenceAdapter(
            SpringDataSavingsGoalRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public SavingsGoal save(SavingsGoal savingsGoal) {

        SavingsGoalJpaEntity entity =
                SavingsGoalPersistenceMapper.toEntity(savingsGoal);

        SavingsGoalJpaEntity saved =
                repository.save(entity);

        return SavingsGoalPersistenceMapper.toDomain(saved);
    }

    @Override
    public List<SavingsGoal> findByUserId(UUID userId) {

        return repository.findByUserId(userId)
                .stream()
                .map(SavingsGoalPersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<SavingsGoal> findByIdAndUserId(
            UUID savingsGoalId,
            UUID userId
    ) {

        return repository
                .findByIdAndUserId(savingsGoalId, userId)
                .map(SavingsGoalPersistenceMapper::toDomain);
    }

    @Override
    public void delete(SavingsGoal savingsGoal) {
        repository.delete(
                SavingsGoalPersistenceMapper.toEntity(savingsGoal)
        );
    }
}
