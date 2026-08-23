package com.quedav1.quedav1back.transaction.domain.model.income;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class Income {

    private final UUID id;
    private final UUID userId;
    private final BigDecimal amount;
    private final Currency currency;
    private final String description;
    private final IncomeCategory category;
    private final LocalDate occurredAt;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Income update(
            BigDecimal amount,
            Currency currency,
            String description,
            IncomeCategory category,
            LocalDate occurredAt
    ) {
        return new Income(
                this.id,
                this.userId,
                amount,
                currency,
                description,
                category,
                occurredAt,
                this.createdAt,
                Instant.now()
        );
    }

    public Income(
            UUID id,
            UUID userId,
            BigDecimal amount,
            Currency currency,
            String description,
            IncomeCategory category,
            LocalDate occurredAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.category = category;
        this.occurredAt = occurredAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public LocalDate getOccurredAt() {
        return occurredAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}