package com.quedav1.quedav1back.transaction.adapter.in.web.transaction;

import com.quedav1.quedav1back.transaction.domain.model.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionRequest(

        UUID userId,

        @NotNull
        @Positive
        BigDecimal amount,

        @NotNull
        TransactionType type,

        String description,

        @NotNull
        LocalDate date
) {
}
