package com.quedav1.quedav1back.transaction.domain.model.saving;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class SavingsGoal {

    private final UUID id;
    private final UUID userId;
    private final String name;
    private final BigDecimal targetAmount;
    private final BigDecimal currentAmount;
    private final Currency currency;
    private final LocalDate targetDate;
    private final SavingsGoalStatus status;
    private final Instant createdAt;
    private final Instant updatedAt;

    public SavingsGoal contribute(BigDecimal amount) {

        BigDecimal newCurrentAmount =
                this.currentAmount.add(amount);

        SavingsGoalStatus newStatus =
                newCurrentAmount.compareTo(this.targetAmount) >= 0
                        ? SavingsGoalStatus.COMPLETED
                        : this.status;

        return new SavingsGoal(
                this.id,
                this.userId,
                this.name,
                this.targetAmount,
                newCurrentAmount,
                this.currency,
                this.targetDate,
                newStatus,
                this.createdAt,
                Instant.now()
        );
    }

    public SavingsGoal(
            UUID id,
            UUID userId,
            String name,
            BigDecimal targetAmount,
            BigDecimal currentAmount,
            Currency currency,
            LocalDate targetDate,
            SavingsGoalStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.currency = currency;
        this.targetDate = targetDate;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public SavingsGoalStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
