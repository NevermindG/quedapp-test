package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.saving.SavingsContributionPersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.saving.SpringDataSavingsContributionRepository;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.saving.SavingsGoalPersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.saving.SpringDataSavingsGoalRepository;
import com.quedav1.quedav1back.transaction.adapter.out.transaction.TransactionalCreateSavingsContributionUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.financial.engine.*;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsContributionRepository;
import com.quedav1.quedav1back.transaction.application.port.out.SavingsGoalRepository;
import com.quedav1.quedav1back.transaction.application.port.service.financial.engine.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class SavingConfiguration {

    @Bean
    public SavingsGoalRepository savingsGoalRepository(
            SpringDataSavingsGoalRepository repository
    ) {
        return new SavingsGoalPersistenceAdapter(repository);
    }

    @Bean
    public CreateSavingsGoalUseCase createSavingsGoalUseCase(
            SavingsGoalRepository savingsGoalRepository
    ) {
        return new CreateSavingsGoalService(
                savingsGoalRepository
        );
    }

    @Bean
    public SavingsContributionRepository savingsContributionRepository(
            SpringDataSavingsContributionRepository repository
    ) {
        return new SavingsContributionPersistenceAdapter(repository);
    }

    @Bean
    public CreateSavingsContributionUseCase createSavingsContributionUseCase(
            SavingsGoalRepository savingsGoalRepository,
            SavingsContributionRepository savingsContributionRepository,
            PlatformTransactionManager transactionManager
    ) {

        CreateSavingsContributionUseCase service =
                new CreateSavingsContributionService(
                        savingsGoalRepository,
                        savingsContributionRepository
                );

        TransactionTemplate transactionTemplate =
                new TransactionTemplate(transactionManager);

        return new TransactionalCreateSavingsContributionUseCase(
                service,
                transactionTemplate
        );
    }

    @Bean
    public GetSavingsGoalsUseCase getSavingsGoalsUseCase(
            SavingsGoalRepository savingsGoalRepository
    ) {

        return new GetSavingsGoalsService(
                savingsGoalRepository
        );
    }

    @Bean
    public GetSavingsGoalUseCase getSavingsGoalUseCase(
            SavingsGoalRepository savingsGoalRepository
    ) {

        return new GetSavingsGoalService(
                savingsGoalRepository
        );
    }

    @Bean
    public GetSavingsContributionsUseCase getSavingsContributionsUseCase(
            SavingsGoalRepository savingsGoalRepository,
            SavingsContributionRepository savingsContributionRepository
    ) {

        return new GetSavingsContributionsService(
                savingsGoalRepository,
                savingsContributionRepository
        );
    }

    @Bean
    public CancelSavingsGoalUseCase cancelSavingsGoalUseCase(
            SavingsGoalRepository savingsGoalRepository
    ) {

        return new CancelSavingsGoalService(
                savingsGoalRepository
        );
    }
}