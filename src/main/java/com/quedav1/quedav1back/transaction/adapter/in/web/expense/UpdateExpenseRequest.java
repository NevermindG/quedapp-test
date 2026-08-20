package com.quedav1.quedav1back.transaction.adapter.in.web.expense;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.Instant;

public record UpdateExpenseRequest(
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        Instant occurredAt
) {
}
