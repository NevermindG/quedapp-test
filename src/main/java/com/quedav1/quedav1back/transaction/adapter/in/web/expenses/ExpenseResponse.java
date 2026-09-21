package com.quedav1.quedav1back.transaction.adapter.in.web.expenses;

import com.quedav1.quedav1back.transaction.application.port.in.expenses.ExpenseResult;
import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expenses.ExpenseCategory;

import java.math.BigDecimal;
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
