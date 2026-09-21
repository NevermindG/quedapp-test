package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.saving;

import com.quedav1.quedav1back.transaction.domain.model.Currency;
import com.quedav1.quedav1back.transaction.domain.model.financial.saving.SavingsGoal;
import com.quedav1.quedav1back.transaction.domain.model.financial.saving.SavingsGoalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record SavingsGoalResult(
        UUID id,
        UUID userId,
        String name,
        BigDecimal targetAmount,
        BigDecimal currentAmount,
        Currency currency,
        LocalDate targetDate,
        SavingsGoalStatus status
) {

    public static SavingsGoalResult from(SavingsGoal goal) {
        return new SavingsGoalResult(
                goal.getId(),
                goal.getUserId(),
                goal.getName(),
                goal.getTargetAmount(),
                goal.getCurrentAmount(),
                goal.getCurrency(),
                goal.getTargetDate(),
                goal.getStatus()
        );
    }
}
