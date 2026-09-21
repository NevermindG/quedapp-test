package com.quedav1.quedav1back.transaction.domain.model.financial.budget;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Budget {

    private final UUID id;
    private final UUID userId;
    private final ExpenseCategory category;
    private final BigDecimal amount;
    private final Currency currency;
    private final int year;
    private final int month;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Budget(
            UUID id,
            UUID userId,
            ExpenseCategory category,
            BigDecimal amount,
            Currency currency,
            int year,
            int month,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.category = category;
        this.amount = amount;
        this.currency = currency;
        this.year = year;
        this.month = month;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Budget updateAmount(BigDecimal newAmount) {

        return new Budget(
                id,
                userId,
                category,
                newAmount,
                currency,
                year,
                month,
                createdAt,
                Instant.now()
        );
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}