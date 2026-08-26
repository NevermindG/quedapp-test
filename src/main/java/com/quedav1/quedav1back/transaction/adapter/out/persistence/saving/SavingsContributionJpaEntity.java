package com.quedav1.quedav1back.transaction.adapter.out.persistence.saving;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "savings_contributions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavingsContributionJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID savingsGoalId;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Instant createdAt;
}
