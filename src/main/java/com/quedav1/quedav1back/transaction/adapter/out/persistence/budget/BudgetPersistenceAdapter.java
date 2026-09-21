package com.quedav1.quedav1back.transaction.adapter.out.persistence.budget;

import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.budget.BudgetRepository;
import com.quedav1.quedav1back.transaction.domain.model.financial.budgets.Budget;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class BudgetPersistenceAdapter
        implements BudgetRepository {

    private final SpringDataBudgetRepository repository;
    private final BudgetPersistenceMapper mapper;

    public BudgetPersistenceAdapter(
            SpringDataBudgetRepository repository,
            BudgetPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Budget save(Budget budget) {

        BudgetJpaEntity entity =
                mapper.toEntity(budget);

        BudgetJpaEntity savedEntity =
                repository.save(entity);

        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Budget>
    findByUserIdAndCategoryAndYearAndMonth(
            UUID userId,
            ExpenseCategory category,
            int year,
            int month
    ) {

        return repository
                .findByUserIdAndCategoryAndYearAndMonth(
                        userId,
                        category,
                        year,
                        month
                )
                .map(mapper::toDomain);
    }

    @Override
    public List<Budget> findByUserIdAndYearAndMonth(
            UUID userId,
            int year,
            int month
    ) {

        return repository
                .findByUserIdAndYearAndMonth(
                        userId,
                        year,
                        month
                )
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Budget> findByIdAndUserId(
            UUID id,
            UUID userId
    ) {

        return repository
                .findByIdAndUserId(
                        id,
                        userId
                )
                .map(mapper::toDomain);
    }

    @Override
    public void delete(Budget budget) {

        repository.delete(
                mapper.toEntity(budget)
        );
    }
}
