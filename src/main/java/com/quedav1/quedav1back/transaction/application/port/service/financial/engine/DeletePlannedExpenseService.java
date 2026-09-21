package com.quedav1.quedav1back.transaction.application.port.service.financial.engine;

import com.quedav1.quedav1back.transaction.application.exception.PlannedExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.DeletePlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpense;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpenseAlreadyPaidException;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpenseStatus;

import java.util.UUID;

public class DeletePlannedExpenseService
        implements DeletePlannedExpenseUseCase {

    private final PlannedExpenseRepository plannedExpenseRepository;

    public DeletePlannedExpenseService(
            PlannedExpenseRepository plannedExpenseRepository
    ) {
        this.plannedExpenseRepository =
                plannedExpenseRepository;
    }

    @Override
    public void delete(
            UUID plannedExpenseId,
            UUID userId
    ) {

        PlannedExpense plannedExpense =
                plannedExpenseRepository
                        .findByIdAndUserId(
                                plannedExpenseId,
                                userId
                        )
                        .orElseThrow(
                                PlannedExpenseNotFoundException::new
                        );

        if (
                plannedExpense.getStatus()
                        == PlannedExpenseStatus.PAID
        ) {
            throw new PlannedExpenseAlreadyPaidException();
        }

        plannedExpenseRepository.delete(
                plannedExpense
        );
    }
}
