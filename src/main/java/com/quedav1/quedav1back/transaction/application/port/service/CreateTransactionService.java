package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.CreateTransactionCommand;
import com.quedav1.quedav1back.transaction.application.port.in.CreateTransactionUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.TransactionResult;
import com.quedav1.quedav1back.transaction.application.port.out.TransactionRepository;
import com.quedav1.quedav1back.transaction.domain.model.Transaction;

import java.util.UUID;

public class CreateTransactionService implements CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;

    public CreateTransactionService(
            TransactionRepository transactionRepository
    ) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResult create(CreateTransactionCommand command) {
        Transaction transaction = new Transaction(
                UUID.randomUUID(),
                command.userId(),
                command.amount(),
                command.type(),
                command.description(),
                command.date()
        );

        Transaction savedTransaction =
                transactionRepository.save(transaction);

        return new TransactionResult(
                savedTransaction.getId(),
                savedTransaction.getAmount(),
                savedTransaction.getDescription(),
                savedTransaction.getDate()
        );
    }
}
