package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.exception.ExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.DeleteExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;

import java.util.UUID;

public class DeleteExpenseService implements DeleteExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public DeleteExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void deleteExpense(UUID expenseId, UUID userId) {
        Expense expense = expenseRepository
                .findByIdAndUserId(expenseId, userId)
                .orElseThrow(ExpenseNotFoundException::new);

        expenseRepository.delete(expense);
    }
}
