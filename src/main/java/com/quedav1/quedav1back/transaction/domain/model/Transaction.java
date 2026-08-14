package com.quedav1.quedav1back.transaction.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final UUID userId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final String description;
    private final LocalDate date;

    public Transaction(
            UUID id,
            UUID userId,
            BigDecimal amount,
            TransactionType type,
            String description,
            LocalDate date
    ) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.date = date;
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

    public TransactionType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }
}
