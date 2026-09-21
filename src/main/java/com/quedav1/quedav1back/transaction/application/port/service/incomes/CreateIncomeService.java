package com.quedav1.quedav1back.transaction.application.port.service.incomes;

import com.quedav1.quedav1back.transaction.application.exception.UserNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.incomes.CreateIncomeCommand;
import com.quedav1.quedav1back.transaction.application.port.in.incomes.CreateIncomeUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;
import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.common.UserCurrencyValidator;
import com.quedav1.quedav1back.transaction.domain.model.User;
import com.quedav1.quedav1back.transaction.domain.model.incomes.Income;

import java.time.Instant;
import java.util.UUID;

public class CreateIncomeService
        implements CreateIncomeUseCase {

    private final IncomeRepository incomeRepository;
    private final UserRepository userRepository;

    public CreateIncomeService(
            IncomeRepository incomeRepository, UserRepository userRepository
    ) {
        this.incomeRepository = incomeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public IncomeResult create(
            CreateIncomeCommand command
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

        Instant now = Instant.now();

        Income income = new Income(
                UUID.randomUUID(),
                command.userId(),
                command.amount(),
                command.currency(),
                command.description(),
                command.category(),
                command.occurredAt(),
                now,
                now
        );

        Income savedIncome =
                incomeRepository.save(income);

        return IncomeResult.from(savedIncome);
    }
}
