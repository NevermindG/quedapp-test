package com.quedav1.quedav1back.transaction.application.port.in;

import java.util.List;
import java.util.UUID;

public interface GetIncomesUseCase {
    List<IncomeResult> getIncomes(UUID userId);
}
