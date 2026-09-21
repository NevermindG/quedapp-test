package com.quedav1.quedav1back.transaction.application.port.in.incomes;

import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;

import java.util.List;
import java.util.UUID;

public interface GetIncomesUseCase {
    List<IncomeResult> getIncomes(UUID userId);
}
