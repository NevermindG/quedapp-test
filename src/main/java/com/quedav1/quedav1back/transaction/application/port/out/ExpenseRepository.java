package com.quedav1.quedav1back.transaction.application.port.out;

import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseRepository {

    Expense save(Expense expense);
    List<Expense> findByUserId(UUID userId);
    Optional<Expense> findByIdAndUserId(
            UUID expenseId,
            UUID userId
    );
    Optional<Expense> findById(UUID expenseId);
    void delete(Expense expense);

    List<Expense> findByUserIdAndOccurredAtBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    );
}
