package com.quedav1.quedav1back.transaction.domain.model.financial.savings.exceptions;

public class SavingsGoalCancelledException
        extends RuntimeException {

    public SavingsGoalCancelledException() {
        super("Cannot contribute to a cancelled savings goal");
    }
}