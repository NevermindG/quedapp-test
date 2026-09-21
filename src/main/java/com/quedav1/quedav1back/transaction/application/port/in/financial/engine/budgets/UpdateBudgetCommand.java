package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budgets;

import java.math.BigDecimal;

public record UpdateBudgetCommand(
        BigDecimal amount
) {
}
