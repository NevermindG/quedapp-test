package com.quedav1.quedav1back.transaction.adapter.out.transaction;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.CreateSavingsContributionCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.CreateSavingsContributionUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.SavingsContributionResult;
import org.springframework.transaction.support.TransactionTemplate;

public class TransactionalCreateSavingsContributionUseCase
        implements CreateSavingsContributionUseCase {

    private final CreateSavingsContributionUseCase delegate;
    private final TransactionTemplate transactionTemplate;

    public TransactionalCreateSavingsContributionUseCase(
            CreateSavingsContributionUseCase delegate,
            TransactionTemplate transactionTemplate
    ) {
        this.delegate = delegate;
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public SavingsContributionResult create(
            CreateSavingsContributionCommand command
    ) {

        return transactionTemplate.execute(status ->
                delegate.create(command)
        );
    }
}
