package com.quedav1.quedav1back.transaction.application.exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException() {
        super("Expense not found");
    }
}
