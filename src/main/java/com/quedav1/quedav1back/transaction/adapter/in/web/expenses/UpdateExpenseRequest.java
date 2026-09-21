package com.quedav1.quedav1back.transaction.adapter.in.web.expenses;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateExpenseRequest(
        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be greater than zero")
        BigDecimal amount,

        @NotNull(message = "Currency is required")
        Currency currency,

        @Size(
                max = 255,
                message = "Description must not exceed 255 characters"
        )
        String description,

        @NotNull(message = "Category is required")
        ExpenseCategory category,

        @NotNull(message = "OccurredAt is required")
        LocalDate occurredAt
) {
}
