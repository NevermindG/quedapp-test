package com.quedav1.quedav1back.transaction.application.port.in.expenses;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record UpdateExpenseCommand(
        UUID expenseId,
        UUID userId,
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        LocalDate occurredAt
) {}
