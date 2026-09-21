package com.quedav1.quedav1back.transaction.application.port.service.incomes;

import com.quedav1.quedav1back.transaction.application.exception.IncomeNotFoundException;
import com.quedav1.quedav1back.transaction.application.port.in.incomes.DeleteIncomeUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;
import com.quedav1.quedav1back.transaction.domain.model.incomes.Income;

import java.util.UUID;

public class DeleteIncomeService implements DeleteIncomeUseCase {

    private final IncomeRepository incomeRepository;

    public DeleteIncomeService(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public void delete(UUID incomeId, UUID userId) {

        Income income = incomeRepository
                .findByIdAndUserId(incomeId, userId)
                .orElseThrow(IncomeNotFoundException::new);

        incomeRepository.delete(income);
    }
}
