package com.quedav1.quedav1back.transaction.adapter.in.web.financial.savinggoal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateSavingsContributionRequest(
        BigDecimal amount,
        LocalDate date
) {
}
