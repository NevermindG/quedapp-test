package com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpenses;

public class PlannedExpenseAlreadyPaidException
        extends RuntimeException {

    public PlannedExpenseAlreadyPaidException() {
        super("Planned expense is already paid");
    }
}
