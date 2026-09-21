package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.budget.BudgetPersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.budget.BudgetPersistenceMapper;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.budget.SpringDataBudgetRepository;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.budget.*;
import com.quedav1.quedav1back.transaction.application.port.out.financial.engine.budget.BudgetRepository;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.budget.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BudgetConfiguration {

    @Bean
    public BudgetPersistenceMapper budgetPersistenceMapper() {
        return new BudgetPersistenceMapper();
    }

    @Bean
    public BudgetRepository budgetRepository(
            SpringDataBudgetRepository repository,
            BudgetPersistenceMapper mapper
    ) {

        return new BudgetPersistenceAdapter(
                repository,
                mapper
        );
    }

    @Bean
    public CreateBudgetUseCase createBudgetUseCase(
            BudgetRepository budgetRepository,
            UserRepository userRepository
    ) {

        return new CreateBudgetService(
                budgetRepository,
                userRepository
        );
    }

    @Bean
    public GetCurrentBudgetsUseCase getCurrentBudgetsUseCase(
            BudgetRepository budgetRepository,
            ExpenseRepository expenseRepository,
            UserRepository userRepository
    ) {

        return new GetCurrentBudgetsService(
                budgetRepository,
                expenseRepository,
                userRepository
        );
    }

    @Bean
    public GetBudgetUseCase getBudgetUseCase(
            BudgetRepository budgetRepository
    ) {

        return new GetBudgetService(
                budgetRepository
        );
    }

    @Bean
    public UpdateBudgetUseCase updateBudgetUseCase(
            BudgetRepository budgetRepository
    ) {

        return new UpdateBudgetService(
                budgetRepository
        );
    }

    @Bean
    public DeleteBudgetUseCase deleteBudgetUseCase(
            BudgetRepository budgetRepository
    ) {

        return new DeleteBudgetService(
                budgetRepository
        );
    }
}
