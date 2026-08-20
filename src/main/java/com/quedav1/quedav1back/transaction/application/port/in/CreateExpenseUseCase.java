package com.quedav1.quedav1back.transaction.application.port.in;

public interface CreateExpenseUseCase {
    ExpenseResult create(CreateExpenseCommand command);
}
