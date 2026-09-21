package com.quedav1.quedav1back.transaction.application.port.service.incomes;

import com.quedav1.quedav1back.transaction.application.port.in.incomes.GetIncomesUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;
import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;

import java.util.List;
import java.util.UUID;

public class GetIncomesService implements GetIncomesUseCase {

    private final IncomeRepository incomeRepository;

    public GetIncomesService(
            IncomeRepository incomeRepository
    ) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<IncomeResult> getIncomes(UUID userId) {

        return incomeRepository
                .findByUserId(userId)
                .stream()
                .map(IncomeResult::from)
                .toList();
    }
}