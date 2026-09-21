package com.quedav1.quedav1back.transaction.application.port.in.incomes;

import com.quedav1.quedav1back.transaction.application.port.in.IncomeResult;

public interface UpdateIncomeUseCase {
    IncomeResult update(UpdateIncomeCommand command);
}
