package com.quedav1.quedav1back.transaction.adapter.in.web.common;

import com.quedav1.quedav1back.transaction.application.exception.*;
import com.quedav1.quedav1back.transaction.domain.model.financial.plannedexpense.PlannedExpenseAlreadyPaidException;
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

    @ExceptionHandler(SavingsGoalNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSavingsGoalNotFound(
            SavingsGoalNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponse(
                                "SAVINGS_GOAL_NOT_FOUND",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(SavingsContributionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSavingsContributionNotFound(
            SavingsContributionNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponse(
                                "SAVINGS_CONTRIBUTION_NOT_FOUND",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(SavingsGoalAlreadyCancelledException.class)
    public ResponseEntity<ErrorResponse> handleSavingsGoalAlreadyCancelled(
            SavingsGoalAlreadyCancelledException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponse(
                                "SAVINGS_GOAL_ALREADY_CANCELLED",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(SavingsGoalAlreadyCompletedException.class)
    public ResponseEntity<ErrorResponse> handleSavingsGoalAlreadyCompleted(
            SavingsGoalAlreadyCompletedException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponse(
                                "SAVINGS_GOAL_ALREADY_COMPLETED",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(SavingsGoalCancelledException.class)
    public ResponseEntity<ErrorResponse> handleSavingsGoalCancelled(
            SavingsGoalCancelledException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponse(
                                "SAVINGS_GOAL_CANCELLED",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(InvalidSavingsContributionException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSavingsContribution(
            InvalidSavingsContributionException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                "INVALID_SAVINGS_CONTRIBUTION",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(BudgetAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleBudgetAlreadyExists(
            BudgetAlreadyExistsException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        new ErrorResponse(
                                "BUDGET_ALREADY_EXISTS",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(InvalidBudgetException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBudget(
            InvalidBudgetException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                "INVALID_BUDGET",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(BudgetNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBudgetNotFound(
            BudgetNotFoundException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        new ErrorResponse(
                                "BUDGET_NOT_FOUND",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(CurrencyMismatchException.class)
    public ResponseEntity<ErrorResponse> handleCurrencyMismatch(
            CurrencyMismatchException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                "CURRENCY_MISMATCH",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(InvalidTimezoneException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTimezone(
            InvalidTimezoneException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                "INVALID_TIMEZONE",
                                exception.getMessage()
                        )
                );
    }

    @ExceptionHandler(InvalidPlannedExpenseException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPlannedExpense(
            InvalidPlannedExpenseException exception
    ) {

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        new ErrorResponse(
                                "INVALID_PLANNED_EXPENSE",
                                exception.getMessage()
                        )
                );
    }
}