package com.quedav1.quedav1back.transaction.domain.model.financial;

public record FinancialScore(
        int score,
        FinancialScoreLevel level,
        int spendingCommitmentPoints,
        int savingsPoints,
        int spendingTrendPoints,
        boolean spendingTrendIncluded
) {
}
