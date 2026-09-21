package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budget;

import java.math.BigDecimal;

public record UpdateBudgetCommand(
        BigDecimal amount
) {
}
