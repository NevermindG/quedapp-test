package com.quedav1.quedav1back.transaction.application.exception;

public class BudgetNotFoundException
        extends RuntimeException {

    public BudgetNotFoundException() {
        super("Budget not found");
    }
}
