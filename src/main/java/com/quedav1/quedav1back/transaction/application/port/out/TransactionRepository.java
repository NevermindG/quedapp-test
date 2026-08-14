package com.quedav1.quedav1back.transaction.application.port.out;

import com.quedav1.quedav1back.transaction.domain.model.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
}
