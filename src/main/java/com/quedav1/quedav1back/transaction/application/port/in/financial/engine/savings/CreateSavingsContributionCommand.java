package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateSavingsContributionCommand(
        UUID savingsGoalId,
        UUID userId,
        BigDecimal amount,
        LocalDate date
) {
}
