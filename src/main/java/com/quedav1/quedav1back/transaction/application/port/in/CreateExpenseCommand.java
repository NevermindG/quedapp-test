package com.quedav1.quedav1back.transaction.application.port.in;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CreateExpenseCommand(
        UUID userId,
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        Instant occurredAt
) {
}