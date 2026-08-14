package com.quedav1.quedav1back.transaction.application.port.in;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResult(
        UUID id,
        BigDecimal amount,
        String description,
        LocalDate date
) {}
