package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.income.IncomePersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.income.SpringDataIncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.in.*;
import com.quedav1.quedav1back.transaction.application.port.out.IncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IncomeConfiguration {

    @Bean
    public IncomeRepository incomeRepository(
            SpringDataIncomeRepository repository
    ) {
        return new IncomePersistenceAdapter(repository);
    }

    @Bean
    public CreateIncomeUseCase createIncomeUseCase(
            IncomeRepository incomeRepository
    ) {
        return new CreateIncomeService(incomeRepository);
    }

    @Bean
    public GetIncomesUseCase getIncomesUseCase(
            IncomeRepository incomeRepository
    ) {
        return new GetIncomesService(incomeRepository);
    }

    @Bean
    public GetIncomeUseCase getIncomeUseCase(
            IncomeRepository incomeRepository
    ) {
        return new GetIncomeService(incomeRepository);
    }

    @Bean
    public UpdateIncomeUseCase updateIncomeUseCase(
            IncomeRepository incomeRepository
    ) {
        return new UpdateIncomeService(incomeRepository);
    }

    @Bean
    public DeleteIncomeUseCase deleteIncomeUseCase(
            IncomeRepository incomeRepository
    ) {
        return new DeleteIncomeService(incomeRepository);
    }
}
