package com.quedav1.quedav1back.transaction.application.port.out;

import com.quedav1.quedav1back.transaction.domain.model.income.Income;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IncomeRepository {

    Income save(Income income);

    List<Income> findByUserId(UUID userId);

    Optional<Income> findByIdAndUserId(
            UUID incomeId,
            UUID userId
    );

    void delete(Income income);

    List<Income> findByUserIdAndOccurredAtBetween(
            UUID userId,
            LocalDate from,
            LocalDate to
    );
}
