package com.quedav1.quedav1back.transaction.application.port.in;

import java.util.UUID;

public interface DeleteIncomeUseCase {
    void delete(UUID incomeId, UUID userId);
}
