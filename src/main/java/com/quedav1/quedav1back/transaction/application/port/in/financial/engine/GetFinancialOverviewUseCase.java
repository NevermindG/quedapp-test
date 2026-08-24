package com.quedav1.quedav1back.transaction.application.port.in.financial.engine;

import java.util.UUID;

public interface GetFinancialOverviewUseCase {

    FinancialOverviewResult getOverview(UUID userId);
}