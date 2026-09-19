package com.quedav1.quedav1back.transaction.application.exception;

public class SavingsGoalAlreadyCancelledException
        extends RuntimeException {

    public SavingsGoalAlreadyCancelledException() {
        super("Savings goal is already cancelled");
    }
}
