package com.quedav1.quedav1back.transaction.application.port.service.common;

import com.quedav1.quedav1back.transaction.application.exception.CurrencyMismatchException;
import com.quedav1.quedav1back.transaction.domain.model.Currency;

public final class UserCurrencyValidator {

    private UserCurrencyValidator() {
    }

    public static void validate(
            Currency userCurrency,
            Currency requestedCurrency
    ) {

        if (
                requestedCurrency == null
                        || userCurrency != requestedCurrency
        ) {
            throw new CurrencyMismatchException();
        }
    }
}
