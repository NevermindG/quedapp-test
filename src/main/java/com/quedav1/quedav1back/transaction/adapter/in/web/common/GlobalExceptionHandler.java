package com.quedav1.quedav1back.transaction.adapter.in.web.common;

import com.quedav1.quedav1back.transaction.application.exception.*;
import com.quedav1.quedav1back.transaction.domain.model.plannedexpense.PlannedExpenseAlreadyPaidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentials(
            InvalidCredentialsException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(
                        "INVALID_CREDENTIALS",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleExpenseNotFound(
            ExpenseNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "EXPENSE_NOT_FOUND",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "USER_NOT_FOUND",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(IncomeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIncomeNotFound(
            IncomeNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "INCOME_NOT_FOUND",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(PlannedExpenseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlannedExpenseNotFound(
            PlannedExpenseNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(
                        "PLANNED_EXPENSE_NOT_FOUND",
                        exception.getMessage()
                ));
    }

    @ExceptionHandler(PlannedExpenseAlreadyPaidException.class)
    public ResponseEntity<ErrorResponse> handlePlannedExpenseAlreadyPaid(
            PlannedExpenseAlreadyPaidException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        "PLANNED_EXPENSE_ALREADY_PAID",
                        exception.getMessage()
                ));
    }
}