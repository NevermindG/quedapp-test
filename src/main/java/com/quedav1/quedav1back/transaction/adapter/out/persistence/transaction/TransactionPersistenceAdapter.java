package com.quedav1.quedav1back.transaction.adapter.out.persistence.transaction;

import com.quedav1.quedav1back.transaction.application.port.out.TransactionRepository;
import com.quedav1.quedav1back.transaction.domain.model.Transaction;

public class TransactionPersistenceAdapter implements TransactionRepository {

    private final SpringDataTransactionRepository repository;

    public TransactionPersistenceAdapter(
            SpringDataTransactionRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionJpaEntity entity =
                TransactionPersistenceMapper.toEntity(transaction);

        TransactionJpaEntity saved =
                repository.save(entity);

        return TransactionPersistenceMapper.toDomain(saved);
    }
}
