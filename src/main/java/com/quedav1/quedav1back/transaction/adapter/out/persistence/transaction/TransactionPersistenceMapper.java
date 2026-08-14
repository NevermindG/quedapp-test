package com.quedav1.quedav1back.transaction.adapter.out.persistence.transaction;

import com.quedav1.quedav1back.transaction.domain.model.Transaction;

public final class TransactionPersistenceMapper {

    private TransactionPersistenceMapper() {
    }

    public static TransactionJpaEntity toEntity(Transaction transaction) {

        return new TransactionJpaEntity(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDescription(),
                transaction.getDate()
        );
    }

    public static Transaction toDomain(TransactionJpaEntity entity) {

        return new Transaction(
                entity.getId(),
                entity.getUserId(),
                entity.getAmount(),
                entity.getType(),
                entity.getDescription(),
                entity.getDate()
        );
    }
}
