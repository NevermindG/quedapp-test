package com.quedav1.quedav1back.transaction.application.exception;

public class SavingsGoalNotFoundException
        extends RuntimeException {

    public SavingsGoalNotFoundException() {
        super("Savings goal not found");
    }
}
