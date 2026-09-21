package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateSavingsGoalCommand(
        UUID userId,
        String name,
        BigDecimal targetAmount,
        Currency currency,
        LocalDate targetDate
) {
}
