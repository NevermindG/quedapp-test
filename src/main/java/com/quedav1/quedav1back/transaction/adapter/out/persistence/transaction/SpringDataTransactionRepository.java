package com.quedav1.quedav1back.transaction.adapter.out.persistence.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataTransactionRepository extends JpaRepository<TransactionJpaEntity, UUID> {
}
