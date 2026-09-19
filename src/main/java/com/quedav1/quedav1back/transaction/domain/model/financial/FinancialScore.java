package com.quedav1.quedav1back.transaction.domain.model.financial;

public record FinancialScore(
        Integer score,
        FinancialScoreLevel level,
        int spendingCommitmentPoints,
        int savingsPoints,
        int spendingTrendPoints,
        boolean spendingTrendIncluded,
        boolean calculable
) {
}
