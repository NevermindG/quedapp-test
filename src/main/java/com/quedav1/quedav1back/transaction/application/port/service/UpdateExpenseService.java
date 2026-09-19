package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.exception.ExpenseNotFoundException;
import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.ExpenseResult;
import com.quedav1.quedav1back.transaction.application.port.in.UpdateExpenseCommand;
import com.quedav1.quedav1back.transaction.application.port.in.UpdateExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserCurrencyValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.expense.Expense;

public class UpdateExpenseService implements UpdateExpenseUseCase {

    private final ExpenseRepository expenseRepository;
    private  final UserRepository userRepository;

    public UpdateExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ExpenseResult update(UpdateExpenseCommand command) {

        User user =
                userRepository
                        .findById(command.userId())
                        .orElseThrow(
                                UserNotFoundException::new
                        );

        UserCurrencyValidator.validate(
                user.getCurrency(),
                command.currency()
        );

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
