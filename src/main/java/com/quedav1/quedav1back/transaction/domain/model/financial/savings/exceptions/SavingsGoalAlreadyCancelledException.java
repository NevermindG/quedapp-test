package com.quedav1.quedav1back.transaction.domain.model.financial.savings.exceptions;

public class SavingsGoalAlreadyCancelledException
        extends RuntimeException {

    public SavingsGoalAlreadyCancelledException() {
        super("Savings goal is already cancelled");
    }
}
