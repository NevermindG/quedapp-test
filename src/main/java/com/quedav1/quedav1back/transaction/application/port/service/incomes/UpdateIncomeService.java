package com.quedav1.quedav1back.transaction.application.port.service.incomes;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;
import com.quedav1.quedav1back.transaction.application.port.in.incomes.UpdateIncomeCommand;
import com.quedav1.quedav1back.transaction.application.port.in.incomes.UpdateIncomeUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.FinancialMovementDateValidator;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserCurrencyValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.incomes.Income;

public class UpdateIncomeService implements UpdateIncomeUseCase {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public UpdateIncomeService(
            IncomeRepository incomeRepository, UserRepository userRepository
    ) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public IncomeResult update(
            UpdateIncomeCommand command
    ) {

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

        FinancialMovementDateValidator.validateNotFuture(
                command.occurredAt(),
                user.getTimezone()
        );

        Income income = incomeRepository
                .findByIdAndUserId(
                        command.incomeId(),
                        command.userId()
                )
                .orElseThrow(() ->
                        new RuntimeException("Income not found")
                );

        Income updatedIncome = income.update(
                command.amount(),
                command.currency(),
                command.description(),
                command.category(),
                command.occurredAt()
        );

        Income savedIncome =
                incomeRepository.save(updatedIncome);

        return IncomeResult.from(savedIncome);
    }
}
