package com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense;

import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses.PlannedExpense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlannedExpenseRepository {

    PlannedExpense save(PlannedExpense plannedExpense);

    List<PlannedExpense> findByUserId(UUID userId);

    Optional<PlannedExpense> findByIdAndUserId(
            UUID plannedExpenseId,
            UUID userId
    );

    void delete(PlannedExpense plannedExpense);

    List<PlannedExpense> findPendingByUserIdAndDueDateBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    );

    List<PlannedExpense>
    findPendingByUserIdAndDueDateLessThanEqual(
            UUID userId,
            LocalDate dueDate
    );
}
