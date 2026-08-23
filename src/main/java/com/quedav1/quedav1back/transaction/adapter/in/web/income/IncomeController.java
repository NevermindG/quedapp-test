package com.quedav1.quedav1back.transaction.adapter.in.web.income;

import com.quedav1.quedav1back.transaction.application.port.in.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/incomes")
public class IncomeController {

    private final CreateIncomeUseCase createIncomeUseCase;
    private final GetIncomesUseCase getIncomesUseCase;
    private final GetIncomeUseCase getIncomeUseCase;
    private final UpdateIncomeUseCase updateIncomeUseCase;
    private final DeleteIncomeUseCase deleteIncomeUseCase;

    public IncomeController(
            CreateIncomeUseCase createIncomeUseCase, GetIncomesUseCase getIncomesUseCase, GetIncomeUseCase getIncomeUseCase, UpdateIncomeUseCase updateIncomeUseCase, DeleteIncomeUseCase deleteIncomeUseCase
    ) {
        this.createIncomeUseCase = createIncomeUseCase;
        this.getIncomesUseCase = getIncomesUseCase;
        this.getIncomeUseCase = getIncomeUseCase;
        this.updateIncomeUseCase = updateIncomeUseCase;
        this.deleteIncomeUseCase = deleteIncomeUseCase;
    }

    @PostMapping
    public ResponseEntity<IncomeResult> create(
            @RequestBody CreateIncomeRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        CreateIncomeCommand command =
                new CreateIncomeCommand(
                        userId,
                        request.amount(),
                        request.currency(),
                        request.description(),
                        request.category(),
                        request.occurredAt()
                );

        IncomeResult result =
                createIncomeUseCase.create(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(result);
    }

    @GetMapping
    public ResponseEntity<List<IncomeResult>> getIncomes(
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        List<IncomeResult> result =
                getIncomesUseCase.getIncomes(userId);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{incomeId}")
    public ResponseEntity<IncomeResult> getIncome(
            @PathVariable UUID incomeId,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        IncomeResult result =
                getIncomeUseCase.getIncome(
                        incomeId,
                        userId
                );

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{incomeId}")
    public ResponseEntity<IncomeResult> updateIncome(
            @PathVariable UUID incomeId,
            @RequestBody UpdateIncomeRequest request,
            Authentication authentication
    ) {

        UUID userId =
                (UUID) authentication.getPrincipal();

        UpdateIncomeCommand command =
                new UpdateIncomeCommand(
                        incomeId,
                        userId,
                        request.amount(),
                        request.currency(),
                        request.description(),
                        request.category(),
                        request.occurredAt()
                );

        IncomeResult result =
                updateIncomeUseCase.update(command);

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{incomeId}")
    public ResponseEntity<Void> deleteIncome(
            @PathVariable UUID incomeId,
            Authentication authentication
    ) {

        UUID userId = (UUID) authentication.getPrincipal();

        deleteIncomeUseCase.delete(
                incomeId,
                userId
        );

        return ResponseEntity.noContent().build();
    }
}
