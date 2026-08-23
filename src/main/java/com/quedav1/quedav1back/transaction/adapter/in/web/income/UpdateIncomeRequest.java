package com.quedav1.quedav1back.transaction.adapter.in.web.income;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.income.IncomeCategory;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateIncomeRequest(
        BigDecimal amount,
        Currency currency,
        String description,
        IncomeCategory category,
        LocalDate occurredAt
) {
}
