package com.quedav1.quedav1back.transaction.application.port.in;

import com.quedav1.quedav1back.transaction.domain.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionCommand(
        UUID userId,
        BigDecimal amount,
        TransactionType type,
        String description,
        LocalDate date
) {}
