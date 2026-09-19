package com.quedav1.quedav1back.transaction.application.exception;

public class CurrencyMismatchException extends RuntimeException {

    public CurrencyMismatchException() {
        super("Currency must match the user's currency");
    }
}