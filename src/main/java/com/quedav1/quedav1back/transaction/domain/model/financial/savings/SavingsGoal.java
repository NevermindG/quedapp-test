package com.quedav1.quedav1back.transaction.domain.model.financial.savings;

import com.quedav1.quedav1back.transaction.application.exception.InvalidSavingsContributionException;
import com.quedav1.quedav1back.transaction.application.exception.SavingsGoalAlreadyCancelledException;
import com.quedav1.quedav1back.transaction.application.exception.SavingsGoalAlreadyCompletedException;
import com.quedav1.quedav1back.transaction.application.exception.SavingsGoalCancelledException;
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

        if (status == SavingsGoalStatus.CANCELLED) {
            throw new SavingsGoalCancelledException();
        }

        if (status == SavingsGoalStatus.COMPLETED) {
            throw new SavingsGoalAlreadyCompletedException();
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidSavingsContributionException();
        }

        BigDecimal newCurrentAmount =
                currentAmount.add(amount);

        SavingsGoalStatus newStatus =
                newCurrentAmount.compareTo(targetAmount) >= 0
                        ? SavingsGoalStatus.COMPLETED
                        : SavingsGoalStatus.ACTIVE;

        return new SavingsGoal(
                id,
                userId,
                name,
                targetAmount,
                newCurrentAmount,
                currency,
                targetDate,
                newStatus,
                createdAt,
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

    public SavingsGoal cancel() {

        if (status == SavingsGoalStatus.CANCELLED) {
            throw new SavingsGoalAlreadyCancelledException();
        }

        if (status == SavingsGoalStatus.COMPLETED) {
            throw new SavingsGoalAlreadyCompletedException();
        }

        return new SavingsGoal(
                id,
                userId,
                name,
                targetAmount,
                currentAmount,
                currency,
                targetDate,
                SavingsGoalStatus.CANCELLED,
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
