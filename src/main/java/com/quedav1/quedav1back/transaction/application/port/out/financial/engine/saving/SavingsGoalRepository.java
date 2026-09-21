package com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving;

import com.quedav1.quedav1back.transaction.domain.model.financial.saving.SavingsGoal;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SavingsGoalRepository {

    SavingsGoal save(SavingsGoal savingsGoal);

    List<SavingsGoal> findByUserId(UUID userId);

    Optional<SavingsGoal> findByIdAndUserId(
            UUID savingsGoalId,
            UUID userId
    );

    void delete(SavingsGoal savingsGoal);
}
