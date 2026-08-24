package com.quedav1.quedav1back.transaction.adapter.in.web.financial.plannedexpense;

import java.time.LocalDate;

public record PayPlannedExpenseRequest(
        LocalDate paidAt
) {
}
