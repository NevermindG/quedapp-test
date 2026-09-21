package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

import java.util.List;
import java.util.UUID;

public interface GetSavingsContributionsUseCase {

    List<SavingsContributionHistoricalResult> getContributions(
            UUID savingsGoalId,
            UUID userId
    );
}
