package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsGoalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SavingsContributionResult(
        UUID id,
        UUID savingsGoalId,
        BigDecimal amount,
        LocalDate date,
        BigDecimal goalCurrentAmount,
        SavingsGoalStatus goalStatus
) {
}
