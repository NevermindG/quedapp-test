package com.quedav1.quedav1back.transaction.application.exception;

public class PlannedExpenseNotFoundException
        extends RuntimeException {

    public PlannedExpenseNotFoundException() {
        super("Planned expense not found");
    }
}
