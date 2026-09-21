package com.quedav1.quedav1back.transaction.adapter.in.web.financial.plannedexpenses;

import java.time.LocalDate;

public record PayPlannedExpenseRequest(
        LocalDate paidAt
) {
}
