package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsContribution;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SavingsContributionHistoricalResult(
        UUID id,
        UUID savingsGoalId,
        BigDecimal amount,
        LocalDate date
) {

    public static SavingsContributionHistoricalResult from(
            SavingsContribution contribution
    ) {

        return new SavingsContributionHistoricalResult(
                contribution.getId(),
                contribution.getSavingsGoalId(),
                contribution.getAmount(),
                contribution.getDate()
        );
    }
}
