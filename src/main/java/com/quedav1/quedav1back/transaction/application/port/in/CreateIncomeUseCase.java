package com.quedav1.quedav1back.transaction.application.port.in;

public interface CreateIncomeUseCase {
    IncomeResult create(CreateIncomeCommand command);
}
