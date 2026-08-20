package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.expense.ExpensePersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.expense.SpringDataExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.in.*;
import com.quedav1.quedav1back.transaction.application.port.out.ExpenseRepository;
import com.quedav1.quedav1back.transaction.application.port.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfiguration {

    @Bean
    public ExpenseRepository expenseRepository(
            SpringDataExpenseRepository repository
    ) {
        return new ExpensePersistenceAdapter(repository);
    }

    @Bean
    public CreateExpenseUseCase createExpenseUseCase(
            ExpenseRepository expenseRepository
    ) {
        return new CreateExpenseService(expenseRepository);
    }

    @Bean
    public GetExpensesUseCase getExpensesUseCase(
            ExpenseRepository expenseRepository
    ) {
        return new GetExpensesService(expenseRepository);
    }

    @Bean
    public GetExpenseUseCase getExpenseUseCase(
            ExpenseRepository expenseRepository
    ) {
        return new GetExpenseService(expenseRepository);
    }

    @Bean
    public DeleteExpenseUseCase deleteExpenseUseCase(
            ExpenseRepository expenseRepository
    ) {
        return new DeleteExpenseService(expenseRepository);
    }

    @Bean
    public UpdateExpenseUseCase updateExpenseUseCase(
            ExpenseRepository expenseRepository
    ) {
        return new UpdateExpenseService(expenseRepository);
    }
}
