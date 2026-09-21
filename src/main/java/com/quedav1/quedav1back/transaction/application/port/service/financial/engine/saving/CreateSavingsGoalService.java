package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.saving;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.CreateSavingsGoalCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.CreateSavingsGoalUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.savings.SavingsGoalResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.saving.SavingsGoalRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserCurrencyValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsGoal;
import com.quedav1.quedav1back.transaction.domain.model.financial.savings.SavingsGoalStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CreateSavingsGoalService
        implements CreateSavingsGoalUseCase {

    private final SavingsGoalRepository savingsGoalRepository;
    private final UserRepository userRepository;

    public CreateSavingsGoalService(
            SavingsGoalRepository savingsGoalRepository, UserRepository userRepository
    ) {
        this.savingsGoalRepository = savingsGoalRepository;
        this.userRepository = userRepository;
    }

    @Override
    public SavingsGoalResult create(
            CreateSavingsGoalCommand command
    ) {

        User user =
                userRepository
                        .findById(command.userId())
                        .orElseThrow(
                                UserNotFoundException::new
                        );

        UserCurrencyValidator.validate(
                user.getCurrency(),
                command.currency()
        );

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
