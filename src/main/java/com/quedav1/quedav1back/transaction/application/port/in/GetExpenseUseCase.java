package com.quedav1.quedav1back.transaction.application.port.in;

import java.util.UUID;

public interface GetExpenseUseCase {
    ExpenseResult getExpense(
            UUID expenseId,
            UUID userId
    );
}
