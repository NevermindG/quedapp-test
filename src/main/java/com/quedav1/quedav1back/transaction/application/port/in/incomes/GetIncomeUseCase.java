package com.quedav1.quedav1back.transaction.application.port.in.incomes;

import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;

import java.util.UUID;

public interface GetIncomeUseCase {
    IncomeResult getIncome(
            UUID incomeId,
            UUID userId
    );
}
