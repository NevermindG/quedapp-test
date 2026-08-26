package com.quedav1.quedav1back.transaction.application.port.out;

import com.quedav1.quedav1back.transaction.domain.model.saving.SavingsContribution;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface SavingsContributionRepository {

    SavingsContribution save(
            SavingsContribution contribution
    );

    List<SavingsContribution> findBySavingsGoalIdAndUserId(
            UUID savingsGoalId,
            UUID userId
    );

    List<SavingsContribution> findByUserIdAndDateBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    );
}
