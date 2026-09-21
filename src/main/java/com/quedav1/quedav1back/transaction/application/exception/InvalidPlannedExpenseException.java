package com.quedav1.quedav1back.transaction.application.exception;

public class InvalidPlannedExpenseException
        extends RuntimeException {

    public InvalidPlannedExpenseException(
            String message
    ) {
        super(message);
    }
}
