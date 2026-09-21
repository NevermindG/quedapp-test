package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.income.IncomePersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.income.SpringDataIncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.in.incomes.*;
import com.quedav1.quedav1back.transaction.application.port.out.incomes.IncomeRepository;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.incomes.*;
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
            IncomeRepository incomeRepository,
            UserRepository userRepository
    ) {
        return new CreateIncomeService(
                incomeRepository,
                userRepository
        );
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
            IncomeRepository incomeRepository,
            UserRepository userRepository
    ) {
        return new UpdateIncomeService(
                incomeRepository,
                userRepository
        );
    }

    @Bean
    public DeleteIncomeUseCase deleteIncomeUseCase(
            IncomeRepository incomeRepository
    ) {
        return new DeleteIncomeService(incomeRepository);
    }
}
