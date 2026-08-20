package com.quedav1.quedav1back.transaction.application.port.in;


public interface UpdateExpenseUseCase {
    ExpenseResult update(UpdateExpenseCommand command);
}
