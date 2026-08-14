package com.quedav1.quedav1back.transaction.application.port.in;

public interface CreateTransactionUseCase {

    TransactionResult create(CreateTransactionCommand command);
}
