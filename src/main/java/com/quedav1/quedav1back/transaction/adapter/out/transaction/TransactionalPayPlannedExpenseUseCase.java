package com.quedav1.quedav1back.transaction.adapter.out.transaction;

import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense.PayPlannedExpenseUseCase;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDate;
import java.util.UUID;

public class TransactionalPayPlannedExpenseUseCase
        implements PayPlannedExpenseUseCase {

    private final PayPlannedExpenseUseCase delegate;
    private final TransactionTemplate transactionTemplate;

    public TransactionalPayPlannedExpenseUseCase(
            PayPlannedExpenseUseCase delegate,
            TransactionTemplate transactionTemplate
    ) {
        this.delegate = delegate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public ExpenseResult pay(
            UUID plannedExpenseId,
            UUID userId,
            LocalDate paidAt
    ) {

        return transactionTemplate.execute(status ->
                delegate.pay(
                        plannedExpenseId,
                        userId,
                        paidAt
                )
        );
    }
}
