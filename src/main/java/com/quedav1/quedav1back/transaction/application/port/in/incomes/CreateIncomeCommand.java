package com.quedav1.quedav1back.transaction.application.port.in.incomes;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.incomes.IncomeCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateIncomeCommand(
        UUID userId,
        BigDecimal amount,
        Currency currency,
        String description,
        IncomeCategory category,
        LocalDate occurredAt
) {
}
