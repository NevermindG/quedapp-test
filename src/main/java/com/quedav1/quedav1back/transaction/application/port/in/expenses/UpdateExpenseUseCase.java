package com.quedav1.quedav1back.transaction.application.port.in.expenses;


public interface UpdateExpenseUseCase {
    ExpenseResult update(UpdateExpenseCommand command);
}
