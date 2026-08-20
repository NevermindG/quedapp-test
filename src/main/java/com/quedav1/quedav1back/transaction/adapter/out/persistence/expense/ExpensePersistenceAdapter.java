package com.quedav1.quedav1back.transaction.adapter.out.persistence.expense;

import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ExpensePersistenceAdapter implements ExpenseRepository {

    private final SpringDataExpenseRepository repository;

    public ExpensePersistenceAdapter(
            SpringDataExpenseRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Expense save(Expense expense) {

        ExpenseJpaEntity entity =
                ExpensePersistenceMapper.toEntity(expense);

        ExpenseJpaEntity saved =
                repository.save(entity);

        return ExpensePersistenceMapper.toDomain(saved);
    }

    @Override
    public List<Expense> findByUserId(UUID userId) {
        return repository.findByUserId(userId)
                .stream()
                .map(ExpensePersistenceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Expense> findByIdAndUserId(UUID expenseId, UUID userId) {
        return repository
                .findByIdAndUserId(expenseId, userId)
                .map(ExpensePersistenceMapper::toDomain);
    }

    @Override
    public Optional<Expense> findById(UUID expenseId) {
        return repository
                .findById(expenseId)
                .map(ExpensePersistenceMapper::toDomain);
    }

    @Override
    public void delete(Expense expense) {

        ExpenseJpaEntity entity =
                ExpensePersistenceMapper.toEntity(expense);

        repository.delete(entity);
    }
}
