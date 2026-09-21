package com.quedav1.quedav1back.transaction.domain.model.financial.saving;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class SavingsContribution {

    private final UUID id;
    private final UUID savingsGoalId;
    private final UUID userId;
    private final BigDecimal amount;
    private final LocalDate date;
    private final Instant createdAt;

    public SavingsContribution(
            UUID id,
            UUID savingsGoalId,
            UUID userId,
            BigDecimal amount,
            LocalDate date,
            Instant createdAt
    ) {
        this.id = id;
        this.savingsGoalId = savingsGoalId;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getSavingsGoalId() {
        return savingsGoalId;
    }

    public UUID getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}