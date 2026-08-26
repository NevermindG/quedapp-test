package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreateSavingsGoalCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreateSavingsGoalUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.SavingsGoalResult;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsGoalRepository;
import com.quedav1.quedav1back.transaction.domain.model.saving.SavingsGoal;
import com.quedav1.quedav1back.transaction.domain.model.saving.SavingsGoalStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CreateSavingsGoalService
        implements CreateSavingsGoalUseCase {

    private final SavingsGoalRepository savingsGoalRepository;

    public CreateSavingsGoalService(
            SavingsGoalRepository savingsGoalRepository
    ) {
        this.savingsGoalRepository = savingsGoalRepository;
    }

    @Override
    public SavingsGoalResult create(
            CreateSavingsGoalCommand command
    ) {

        Instant now = Instant.now();

        SavingsGoal goal = new SavingsGoal(
                UUID.randomUUID(),
                command.userId(),
                command.name(),
                command.targetAmount(),
                BigDecimal.ZERO,
                command.currency(),
                command.targetDate(),
                SavingsGoalStatus.ACTIVE,
                now,
                now
        );

        return SavingsGoalResult.from(
                savingsGoalRepository.save(goal)
        );
    }
}
