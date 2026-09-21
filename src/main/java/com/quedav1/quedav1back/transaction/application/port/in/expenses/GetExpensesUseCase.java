package com.quedav1.quedav1back.transaction.application.port.in.expenses;

import java.util.List;
import java.util.UUID;

public interface GetExpensesUseCase {

    List<ExpenseResult> getExpenses(UUID userId);
}
