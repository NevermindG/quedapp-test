package com.quedav1.quedav1back.transaction.adapter.in.web.incomes;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.incomes.IncomeCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateIncomeRequest(
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
        IncomeCategory category,

        @NotNull(message = "OccurredAt is required")
        LocalDate occurredAt

) {
}
