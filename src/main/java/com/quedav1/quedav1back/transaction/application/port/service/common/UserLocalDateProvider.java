package com.quedav1.quedav1back.transaction.application.port.service.common;

import com.quedav1.quedav1back.transaction.application.exception.InvalidTimezoneException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;

public final class UserLocalDateProvider {

    private UserLocalDateProvider() {
    }

    public static LocalDate today(String timezone) {

        try {
            ZoneId zoneId =
                    ZoneId.of(timezone);

            return LocalDate.now(zoneId);

        } catch (
                DateTimeException
                | NullPointerException exception
        ) {
            throw new InvalidTimezoneException();
        }
    }
}