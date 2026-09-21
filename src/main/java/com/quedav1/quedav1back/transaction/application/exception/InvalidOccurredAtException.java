package com.quedav1.quedav1back.transaction.application.exception;

public class InvalidOccurredAtException
        extends RuntimeException {

    public InvalidOccurredAtException(
            String message
    ) {
        super(message);
    }
}
