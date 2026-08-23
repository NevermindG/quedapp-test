package com.quedav1.quedav1back.transaction.adapter.in.web.income;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.income.IncomeCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

public record CreateIncomeRequest(
        BigDecimal amount,
        Currency currency,
        String description,
        IncomeCategory category,
        LocalDate occurredAt
) {
}