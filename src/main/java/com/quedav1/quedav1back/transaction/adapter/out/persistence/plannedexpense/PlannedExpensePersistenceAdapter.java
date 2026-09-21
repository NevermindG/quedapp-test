package com.quedav1.quedav1back.transaction.adapter.out.persistence.plannedexpense;

import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense.PlannedExpense;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense.PlannedExpenseStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PlannedExpensePersistenceAdapter
        implements PlannedExpenseRepository {

    private final SpringDataPlannedExpenseRepository repository;

    public PlannedExpensePersistenceAdapter(
            SpringDataPlannedExpenseRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public PlannedExpense save(PlannedExpense plannedExpense) {

        PlannedExpenseJpaEntity entity =
                PlannedExpensePersistenceMapper.toEntity(plannedExpense);

        PlannedExpenseJpaEntity saved =
                repository.save(entity);

        return PlannedExpensePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<PlannedExpense> findByUserId(UUID userId) {

        return repository.findByUserId(userId)
                .stream()
                .map(PlannedExpensePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<PlannedExpense> findByIdAndUserId(
            UUID plannedExpenseId,
            UUID userId
    ) {

        return repository
                .findByIdAndUserId(plannedExpenseId, userId)
                .map(PlannedExpensePersistenceMapper::toDomain);
    }

    @Override
    public void delete(PlannedExpense plannedExpense) {

        repository.delete(
                PlannedExpensePersistenceMapper.toEntity(plannedExpense)
        );
    }

    @Override
    public List<PlannedExpense> findPendingByUserIdAndDueDateBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    ) {

        return repository
                .findByUserIdAndStatusAndDueDateBetween(
                        userId,
                        PlannedExpenseStatus.PENDING,
                        from,
                        to
                )
                .stream()
                .map(PlannedExpensePersistenceMapper::toDomain)
                .toList();
    }
}
