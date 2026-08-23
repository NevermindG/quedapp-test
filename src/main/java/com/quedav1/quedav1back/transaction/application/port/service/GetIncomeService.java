package com.quedav1.quedav1back.transaction.application.port.service;

import com.quedav1.quedav1back.transaction.application.exception.IncomeNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.GetIncomeUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;
import com.quedav1.quedav1back.transaction.application.port.out.IncomeRepository;
import com.quedav1.quedav1back.transaction.domain.model.income.Income;

import java.util.UUID;

public class GetIncomeService implements GetIncomeUseCase {

    private final IncomeRepository incomeRepository;

    public GetIncomeService(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public IncomeResult getIncome(
            UUID incomeId,
            UUID userId
    ) {

        Income income = incomeRepository
                .findByIdAndUserId(incomeId, userId)
                .orElseThrow(IncomeNotFoundException::new);

        return IncomeResult.from(income);
    }
}
