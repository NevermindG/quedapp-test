package com.quedav1.quedav1back.transaction.application.exception;

public class InvalidSavingsContributionException
        extends RuntimeException {

    public InvalidSavingsContributionException() {
        super("Savings contribution must be greater than zero");
    }
}
