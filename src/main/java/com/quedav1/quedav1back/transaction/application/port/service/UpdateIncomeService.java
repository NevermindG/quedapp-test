package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;
import com.quedav1.quedav1back.transaction.application.port.in.UpdateIncomeCommand;
import com.quedav1.quedav1back.transaction.application.port.in.UpdateIncomeUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.IncomeRepository;
import com.quedav1.quedav1back.transaction.domain.model.income.Income;

public class UpdateIncomeService implements UpdateIncomeUseCase {

    private final IncomeRepository incomeRepository;

    public UpdateIncomeService(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public IncomeResult update(
            UpdateIncomeCommand command
    ) {

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
