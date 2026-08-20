package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.GetExpensesUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;

import java.util.List;
import java.util.UUID;

public class GetExpensesService implements GetExpensesUseCase {

    private final ExpenseRepository expenseRepository;

    public GetExpensesService(
            ExpenseRepository expenseRepository
    ) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<ExpenseResult> getExpenses(UUID userId) {

        return expenseRepository.findByUserId(userId)
                .stream()
                .map(expense -> new ExpenseResult(
                        expense.getId(),
                        expense.getUserId(),
                        expense.getAmount(),
                        expense.getCurrency(),
                        expense.getDescription(),
                        expense.getCategory(),
                        expense.getOccurredAt()
                ))
                .toList();
    }
}
