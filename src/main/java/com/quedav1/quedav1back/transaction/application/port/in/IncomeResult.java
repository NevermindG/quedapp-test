package com.quedav1.quedav1back.transaction.application.port.in;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.incomes.Income;
import com.quedav1.quedav1back.transaction.domain.model.incomes.IncomeCategory;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record IncomeResult(
        UUID id,
        UUID userId,
        BigDecimal amount,
        Currency currency,
        String description,
        IncomeCategory category,
        LocalDate occurredAt
) {

    public static IncomeResult from(Income income) {

        return new IncomeResult(
                income.getId(),
                income.getUserId(),
                income.getAmount(),
                income.getCurrency(),
                income.getDescription(),
                income.getCategory(),
                income.getOccurredAt()
        );
    }
}
