package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.exception.ExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.UpdateExpenseCommand;
import com.quedav1.quedav1back.transaction.application.port.in.UpdateExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;

public class UpdateExpenseService implements UpdateExpenseUseCase {

    private final ExpenseRepository expenseRepository;

    public UpdateExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }


    @Override
    public ExpenseResult update(UpdateExpenseCommand command) {
        Expense expense = expenseRepository
                .findByIdAndUserId(
                        command.expenseId(),
                        command.userId()
                )
                .orElseThrow(
                        ExpenseNotFoundException::new
                );

        Expense updatedExpense = expense.update(
                command.amount(),
                command.currency(),
                command.description(),
                command.category(),
                command.occurredAt()
        );

        Expense savedExpense =
                expenseRepository.save(updatedExpense);

        return ExpenseResult.from(savedExpense);
    }
}
