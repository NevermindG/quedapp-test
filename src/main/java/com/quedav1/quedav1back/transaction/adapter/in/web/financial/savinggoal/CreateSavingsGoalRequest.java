package com.quedav1.quedav1back.transaction.adapter.in.web.financial.savinggoal;

import com.quedav1.quedav1back.transaction.domain.model.Currency;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateSavingsGoalRequest(
        String name,
        BigDecimal targetAmount,
        Currency currency,
        LocalDate targetDate
) {
}
