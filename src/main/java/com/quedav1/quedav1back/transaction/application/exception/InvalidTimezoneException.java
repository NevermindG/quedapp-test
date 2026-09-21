package com.quedav1.quedav1back.transaction.application.exception;

public class InvalidTimezoneException extends RuntimeException {

  public InvalidTimezoneException() {
    super("Invalid user timezone");
  }
}
