package com.quedav1.quedav1back.transaction.adapter.in.web.expenses;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateExpenseRequest(

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        Currency currency,

        String description,

        @NotNull
        ExpenseCategory category,

        @NotNull
        LocalDate occurredAt
) {
}
