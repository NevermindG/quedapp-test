package com.quedav1.quedav1back.transaction.application.port.service.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.application.exception.InvalidPlannedExpenseException;
import com.quedav1.quedav1back.transaction.application.exception.PlannedExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense.PlannedExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense.UpdatePlannedExpenseCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpense.UpdatePlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserCurrencyValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense.PlannedExpense;

import java.math.BigDecimal;
import java.util.UUID;

public class UpdatePlannedExpenseService
        implements UpdatePlannedExpenseUseCase {

    private final PlannedExpenseRepository plannedExpenseRepository;
    private final UserRepository userRepository;

    public UpdatePlannedExpenseService(
            PlannedExpenseRepository plannedExpenseRepository,
            UserRepository userRepository
    ) {
        this.plannedExpenseRepository =
                plannedExpenseRepository;

        this.userRepository =
                userRepository;
    }

    @Override
    public PlannedExpenseResult update(
            UUID plannedExpenseId,
            UUID userId,
            UpdatePlannedExpenseCommand command
    ) {

        validate(command);

        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(
                                UserNotFoundException::new
                        );

        UserCurrencyValidator.validate(
                user.getCurrency(),
                command.currency()
        );

        PlannedExpense plannedExpense =
                plannedExpenseRepository
                        .findByIdAndUserId(
                                plannedExpenseId,
                                userId
                        )
                        .orElseThrow(
                                PlannedExpenseNotFoundException::new
                        );

        PlannedExpense updatedPlannedExpense =
                plannedExpense.update(
                        command.amount(),
                        command.currency(),
                        command.description(),
                        command.category(),
                        command.dueDate()
                );

        PlannedExpense savedPlannedExpense =
                plannedExpenseRepository.save(
                        updatedPlannedExpense
                );

        return PlannedExpenseResult.from(
                savedPlannedExpense
        );
    }

    private void validate(
            UpdatePlannedExpenseCommand command
    ) {

        if (
                command.amount() == null
                        || command.amount()
                        .compareTo(BigDecimal.ZERO) <= 0
        ) {
            throw new InvalidPlannedExpenseException(
                    "Planned expense amount must be greater than zero"
            );
        }

        if (command.currency() == null) {
            throw new InvalidPlannedExpenseException(
                    "Currency is required"
            );
        }

        if (command.category() == null) {
            throw new InvalidPlannedExpenseException(
                    "Category is required"
            );
        }

        if (command.dueDate() == null) {
            throw new InvalidPlannedExpenseException(
                    "Due date is required"
            );
        }
    }
}