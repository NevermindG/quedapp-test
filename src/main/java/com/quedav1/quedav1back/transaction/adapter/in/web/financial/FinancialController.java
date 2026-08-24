package com.quedav1.quedav1back.transaction.adapter.in.web.financial;

import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.FinancialOverviewResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.FinancialSummaryResult;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetFinancialOverviewUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetFinancialSummaryUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/financial")
public class FinancialController {

    private final GetFinancialSummaryUseCase getFinancialSummaryUseCase;
    private final GetFinancialOverviewUseCase getFinancialOverviewUseCase;

    public FinancialController(
            GetFinancialSummaryUseCase getFinancialSummaryUseCase, GetFinancialOverviewUseCase getFinancialOverviewUseCase
    ) {
        this.getFinancialSummaryUseCase =
                getFinancialSummaryUseCase;
        this.getFinancialOverviewUseCase = getFinancialOverviewUseCase;
    }

    @GetMapping("/summary")
    public ResponseEntity<FinancialSummaryResult> getSummary(
            @RequestParam int year,
            @RequestParam int month,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        FinancialSummaryResult result =
                getFinancialSummaryUseCase.getSummary(
                        userId,
                        year,
                        month
                );

        return ResponseEntity.ok(result);
    }

    @GetMapping("/overview")
    public ResponseEntity<FinancialOverviewResult> getOverview(
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        FinancialOverviewResult result =
                getFinancialOverviewUseCase.getOverview(userId);

        return ResponseEntity.ok(result);
    }
}