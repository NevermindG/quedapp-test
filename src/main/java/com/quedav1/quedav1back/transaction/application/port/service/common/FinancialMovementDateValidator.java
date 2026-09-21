package com.quedav1.quedav1back.transaction.application.port.service.common;

import com.quedav1.quedav1back.transaction.application.exception.InvalidOccurredAtException;

import java.time.LocalDate;

public final class FinancialMovementDateValidator {

    private FinancialMovementDateValidator() {
    }

    public static void validateNotFuture(
            LocalDate occurredAt,
            String timezone
    ) {

        if (occurredAt == null) {
            throw new InvalidOccurredAtException(
                    "Occurred date is required"
            );
        }

        LocalDate today =
                UserLocalDateProvider.today(
                        timezone
                );

        if (occurredAt.isAfter(today)) {
            throw new InvalidOccurredAtException(
                    "Occurred date cannot be in the future"
            );
        }
    }
}
