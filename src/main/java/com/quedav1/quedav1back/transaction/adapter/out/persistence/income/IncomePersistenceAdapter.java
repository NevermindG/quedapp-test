package com.quedav1.quedav1back.transaction.adapter.out.persistence.income;

import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;
import com.quedav1.quedav1back.transaction.domain.model.incomes.Income;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class IncomePersistenceAdapter implements IncomeRepository {

    private final SpringDataIncomeRepository repository;

    public IncomePersistenceAdapter(
            SpringDataIncomeRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Income save(Income income) {

        IncomeJpaEntity entity =
                IncomePersistenceMapper.toEntity(income);

        IncomeJpaEntity saved =
                repository.save(entity);

        return IncomePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Income> findByUserId(UUID userId) {

        return repository
                .findByUserId(userId)
                .stream()
                .map(IncomePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Income> findByIdAndUserId(
            UUID incomeId,
            UUID userId
    ) {

        return repository
                .findByIdAndUserId(incomeId, userId)
                .map(IncomePersistenceMapper::toDomain);
    }

    @Override
    public void delete(Income income) {

        IncomeJpaEntity entity =
                IncomePersistenceMapper.toEntity(income);

        repository.delete(entity);
    }

    @Override
    public List<Income> findByUserIdAndOccurredAtBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    ) {
        return repository
                .findByUserIdAndOccurredAtBetween(userId, from, to)
                .stream()
                .map(IncomePersistenceMapper::toDomain)
                .toList();
    }
}
