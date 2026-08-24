package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreatePlannedExpenseCommand;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreatePlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.PlannedExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.out.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpense;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpenseStatus;

import java.time.Instant;
import java.util.UUID;

public class CreatePlannedExpenseService
        implements CreatePlannedExpenseUseCase {

    private final PlannedExpenseRepository plannedExpenseRepository;

    public CreatePlannedExpenseService(
            PlannedExpenseRepository plannedExpenseRepository
    ) {
        this.plannedExpenseRepository = plannedExpenseRepository;
    }

    @Override
    public PlannedExpenseResult create(
            CreatePlannedExpenseCommand command
    ) {

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
