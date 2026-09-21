package com.quedav1.quedav1back.transaction.adapter.in.web.incomes;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.incomes.IncomeCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateIncomeRequest(
        BigDecimal amount,
        Currency currency,
        String description,
        IncomeCategory category,
        LocalDate occurredAt
) {
}