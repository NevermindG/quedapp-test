package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

public interface CreateSavingsContributionUseCase {
    SavingsContributionResult create(
            CreateSavingsContributionCommand command
    );
}
