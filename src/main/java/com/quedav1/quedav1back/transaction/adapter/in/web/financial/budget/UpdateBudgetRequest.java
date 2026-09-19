package com.quedav1.quedav1back.transaction.adapter.in.web.financial.budget;

import java.math.BigDecimal;

public record UpdateBudgetRequest(
        BigDecimal amount
) {
}
