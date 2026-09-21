package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.CreatePlannedExpenseCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.CreatePlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.PlannedExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserCurrencyValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpense;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpenseStatus;

import java.time.Instant;
import java.util.UUID;

public class CreatePlannedExpenseService
        implements CreatePlannedExpenseUseCase {

    private final PlannedExpenseRepository plannedExpenseRepository;
    private final UserRepository userRepository;

    public CreatePlannedExpenseService(
            PlannedExpenseRepository plannedExpenseRepository, UserRepository userRepository
    ) {
        this.plannedExpenseRepository = plannedExpenseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PlannedExpenseResult create(
            CreatePlannedExpenseCommand command
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

        PlannedExpense plannedExpense =
                new PlannedExpense(
                        UUID.randomUUID(),
                        command.userId(),
                        command.amount(),
                        command.currency(),
                        command.description(),
                        command.category(),
                        command.dueDate(),
                        PlannedExpenseStatus.PENDING,
                        now,
                        now
                );

        PlannedExpense saved =
                plannedExpenseRepository.save(plannedExpense);

        return PlannedExpenseResult.from(saved);
    }
}
