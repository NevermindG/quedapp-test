package com.quedav1.quedav1back.transaction.domain.model.plannedexpense;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public class PlannedExpense {

    private final UUID id;
    private final UUID userId;
    private final BigDecimal amount;
    private final Currency currency;
    private final String description;
    private final ExpenseCategory category;
    private final LocalDate dueDate;
    private final PlannedExpenseStatus status;
    private final Instant createdAt;
    private final Instant updatedAt;

    public PlannedExpense markAsPaid() {

        if (this.status == PlannedExpenseStatus.PAID) {
            throw new PlannedExpenseAlreadyPaidException();
        }

        return new PlannedExpense(
                this.id,
                this.userId,
                this.amount,
                this.currency,
                this.description,
                this.category,
                this.dueDate,
                PlannedExpenseStatus.PAID,
                this.createdAt,
                Instant.now()
        );
    }

    public PlannedExpense(
            UUID id,
            UUID userId,
            BigDecimal amount,
            Currency currency,
            String description,
            ExpenseCategory category,
            LocalDate dueDate,
            PlannedExpenseStatus status,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String getDescription() {
        return description;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public PlannedExpenseStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
