package com.quedav1.quedav1back.transaction.application.exception;

public class SavingsContributionNotFoundException extends RuntimeException {

    public SavingsContributionNotFoundException() {
        super("Savings contributions not found");
    }
}
