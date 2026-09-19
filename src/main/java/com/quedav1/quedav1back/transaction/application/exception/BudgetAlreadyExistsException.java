package com.quedav1.quedav1back.transaction.application.exception;

public class BudgetAlreadyExistsException
        extends RuntimeException {

  public BudgetAlreadyExistsException() {
    super("Budget already exists for this category and period");
  }
}
