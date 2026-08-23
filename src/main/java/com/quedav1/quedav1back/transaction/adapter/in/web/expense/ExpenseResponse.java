package com.quedav1.quedav1back.transaction.adapter.in.web.expense;

import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;
import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record ExpenseResponse(
        UUID id,
        BigDecimal amount,
        Currency currency,
        String description,
        ExpenseCategory category,
        LocalDate occurredAt
) {

    public static ExpenseResponse from(ExpenseResult result) {

        return new ExpenseResponse(
                result.id(),
                result.amount(),
                result.currency(),
                result.description(),
                result.category(),
                result.occurredAt()
        );
    }
}
