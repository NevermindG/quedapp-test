package com.quedav1.quedav1back.transaction.application.port.service.common;

import com.quedav1.quedav1back.transaction.application.exception.InvalidTimezoneException;

import java.time.DateTimeException;
import java.time.ZoneId;

public final class UserTimezoneValidator {

    private UserTimezoneValidator() {
    }

    public static void validate(
            String timezone
    ) {

        try {

            ZoneId.of(timezone);

        } catch (
                DateTimeException
                | NullPointerException exception
        ) {

            throw new InvalidTimezoneException();
        }
    }
}
