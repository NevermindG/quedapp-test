package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.plannedexpense.PlannedExpensePersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.plannedexpense.SpringDataPlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.adapter.out.transaction.TransactionalPayPlannedExpenseUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.plannedexpenses.*;
import com.quedav1.quedav1back.transaction.application.port.out.expenses.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.plannedexpense.PlannedExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.plannedexpense.*;
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
            PlannedExpenseRepository plannedExpenseRepository,
            UserRepository userRepository
    ) {
        return new CreatePlannedExpenseService(
                plannedExpenseRepository,
                userRepository
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

    @Bean
    public GetPlannedExpenseUseCase getPlannedExpenseUseCase(
            PlannedExpenseRepository plannedExpenseRepository
    ) {

        return new GetPlannedExpenseService(
                plannedExpenseRepository
        );
    }

    @Bean
    public UpdatePlannedExpenseUseCase updatePlannedExpenseUseCase(
            PlannedExpenseRepository plannedExpenseRepository,
            UserRepository userRepository
    ) {

        return new UpdatePlannedExpenseService(
                plannedExpenseRepository,
                userRepository
        );
    }

    @Bean
    public DeletePlannedExpenseUseCase deletePlannedExpenseUseCase(
            PlannedExpenseRepository plannedExpenseRepository
    ) {

        return new DeletePlannedExpenseService(
                plannedExpenseRepository
        );
    }
}
