package com.quedav1.quedav1back.transaction.domain.model.financial.savings.exceptions;

public class InvalidSavingsContributionException
        extends RuntimeException {

    public InvalidSavingsContributionException() {
        super("Savings contribution must be greater than zero");
    }
}
