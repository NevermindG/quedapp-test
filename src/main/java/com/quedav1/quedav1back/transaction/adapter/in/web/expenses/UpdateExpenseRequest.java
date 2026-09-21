package com.quedav1.quedav1back.transaction.adapter.in.web.expenses;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateExpenseRequest(
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        LocalDate occurredAt
) {
}
