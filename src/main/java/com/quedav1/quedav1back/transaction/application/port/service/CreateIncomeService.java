package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.CreateIncomeCommand;
import com.quedav1.quedav1back.transaction.application.port.in.CreateIncomeUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;
import com.quedav1.quedav1back.transaction.application.port.out.IncomeRepository;
import com.quedav1.quedav1back.transaction.domain.model.income.Income;

import java.time.Instant;
import java.util.UUID;

public class CreateIncomeService
        implements CreateIncomeUseCase {

    private final IncomeRepository incomeRepository;

    public CreateIncomeService(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public IncomeResult create(
            CreateIncomeCommand command
    ) {

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
