package com.quedav1.quedav1back.transaction.application.exception;

public class SavingsGoalAlreadyCompletedException
        extends RuntimeException {

    public SavingsGoalAlreadyCompletedException() {
        super("Completed savings goal cannot be cancelled");
    }
}
