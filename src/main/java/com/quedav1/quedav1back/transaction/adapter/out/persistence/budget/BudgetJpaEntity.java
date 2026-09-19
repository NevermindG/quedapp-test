package com.quedav1.quedav1back.transaction.adapter.out.persistence.budget;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.expense.ExpenseCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "budgets",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_budget_user_category_period",
                        columnNames = {
                                "user_id",
                                "category",
                                "year",
                                "month"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BudgetJpaEntity {

    @Id
    private UUID id;

    @Column(
            name = "user_id",
            nullable = false
    )
    private UUID userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseCategory category;

    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(
            name = "created_at",
            nullable = false
    )
    private Instant createdAt;

    @Column(
            name = "updated_at",
            nullable = false
    )
    private Instant updatedAt;
}
