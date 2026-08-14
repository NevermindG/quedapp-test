package com.quedav1.quedav1back.transaction.adapter.in.web.transaction;

import com.quedav1.quedav1back.transaction.application.port.in.TransactionResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponse(
        UUID id,
        BigDecimal amount,
        String description,
        LocalDate date
) {

    public static TransactionResponse from(TransactionResult result) {
        return new TransactionResponse(
                result.id(),
                result.amount(),
                result.description(),
                result.date()
        );
    }
}