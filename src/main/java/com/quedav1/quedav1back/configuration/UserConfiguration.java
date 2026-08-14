package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.user.BCryptPasswordEncoderAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.user.SpringDataUserRepository;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.user.UserPersistenceAdapter;
import com.quedav1.quedav1back.transaction.application.port.in.CreateUserUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.PasswordEncoder;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.CreateUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return new CreateUserService(userRepository, passwordEncoder);
    }

    @Bean
    public UserRepository userRepository(
            SpringDataUserRepository repository
    ) {
        return new UserPersistenceAdapter(repository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoderAdapter();
    }
}
