package com.quedav1.quedav1back.transaction.application.port.service.expenses;

import com.quedav1.quedav1back.transaction.application.exception.ExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.expenses.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.expenses.GetExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.expenses.ExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.expenses.Expense;

import java.util.UUID;

public class GetExpenseService implements GetExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public GetExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public ExpenseResult getExpense(UUID expenseId, UUID userId) {
        Expense expense = expenseRepository
                .findByIdAndUserId(expenseId, userId)
                .orElseThrow(ExpenseNotFoundException::new);

        return new ExpenseResult(
                expense.getId(),
                expense.getUserId(),
                expense.getAmount(),
                expense.getCurrency(),
                expense.getDescription(),
                expense.getCategory(),
                expense.getOccurredAt()
        );
    }
}
