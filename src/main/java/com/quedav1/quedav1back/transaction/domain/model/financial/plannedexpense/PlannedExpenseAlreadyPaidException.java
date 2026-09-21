package com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense;

public class PlannedExpenseAlreadyPaidException
        extends RuntimeException {

    public PlannedExpenseAlreadyPaidException() {
        super("Planned expense is already paid");
    }
}
