package com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budget;

import java.util.List;
import java.util.UUID;

public interface GetCurrentBudgetsUseCase {

    List<CurrentBudgetResult> getCurrentBudgets(
            UUID userId
    );
}
