package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.plannedexpense.PlannedExpensePersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.plannedexpense.SpringDataPlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.adapter.out.transaction.TransactionalPayPlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.CreatePlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.GetPlannedExpensesUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.PayPlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.CreatePlannedExpenseService;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.GetPlannedExpensesService;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.PayPlannedExpenseService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class PlannedExpenseConfiguration {

    @Bean
    public PlannedExpenseRepository plannedExpenseRepository(
            SpringDataPlannedExpenseRepository repository
    ) {
        return new PlannedExpensePersistenceAdapter(repository);
    }

    @Bean
    public CreatePlannedExpenseUseCase createPlannedExpenseUseCase(
            PlannedExpenseRepository plannedExpenseRepository
    ) {
        return new CreatePlannedExpenseService(
                plannedExpenseRepository
        );
    }

    @Bean
    public GetPlannedExpensesUseCase getPlannedExpensesUseCase(
            PlannedExpenseRepository plannedExpenseRepository
    ) {
        return new GetPlannedExpensesService(
                plannedExpenseRepository
        );
    }

    @Bean
    public PayPlannedExpenseUseCase payPlannedExpenseUseCase(
            PlannedExpenseRepository plannedExpenseRepository,
            ExpenseRepository expenseRepository,
            PlatformTransactionManager transactionManager
    ) {

        PayPlannedExpenseUseCase service =
                new PayPlannedExpenseService(
                        plannedExpenseRepository,
                        expenseRepository
                );

        TransactionTemplate transactionTemplate =
                new TransactionTemplate(transactionManager);

        return new TransactionalPayPlannedExpenseUseCase(
                service,
                transactionTemplate
        );
    }
}
