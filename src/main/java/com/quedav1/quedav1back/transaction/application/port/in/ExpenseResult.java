package com.quedav1.quedav1back.transaction.application.port.in;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ExpenseResult(
        UUID id,
        UUID userId,
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        Instant occurredAt
) {
    public static ExpenseResult from(Expense expense) {
        return new ExpenseResult(
                expense.getId(),
                expense.getUserId(),
                expense.getAmount(),
                expense.getCurrency(),
                expense.getDescription(),
                expense.getCategory(),
                expense.getOccurredAt()
        );
    }
}
