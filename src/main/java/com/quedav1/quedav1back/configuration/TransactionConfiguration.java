package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.transaction.SpringDataTransactionRepository;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.transaction.TransactionPersistenceAdapter;
import com.quedav1.quedav1back.transaction.application.port.in.CreateTransactionUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.TransactionRepository;
import com.quedav1.quedav1back.transaction.application.port.service.CreateTransactionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionConfiguration {

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(
            TransactionRepository transactionRepository
    ) {
        return new CreateTransactionService(transactionRepository);
    }

    @Bean
    public TransactionRepository transactionRepository(
            SpringDataTransactionRepository repository
    ) {
        return new TransactionPersistenceAdapter(repository);
    }
}
