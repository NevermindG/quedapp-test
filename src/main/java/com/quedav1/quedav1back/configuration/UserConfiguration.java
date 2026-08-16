package com.quedav1.quedav1back.configuration;

import com.quedav1.quedav1back.transaction.adapter.out.persistence.user.BCryptPasswordEncoderAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.user.SpringDataUserRepository;
import com.quedav1.quedav1back.transaction.adapter.out.persistence.user.UserPersistenceAdapter;
import com.quedav1.quedav1back.transaction.adapter.out.security.JwtService;
import com.quedav1.quedav1back.transaction.application.port.in.CreateUserUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.GetCurrentUserUseCase;
import com.quedav1.quedav1back.transaction.application.port.in.LoginUseCase;
import com.quedav1.quedav1back.transaction.application.port.out.PasswordEncoder;
import com.quedav1.quedav1back.transaction.application.port.out.TokenProvider;
import com.quedav1.quedav1back.transaction.application.port.out.UserRepository;
import com.quedav1.quedav1back.transaction.application.port.service.CreateUserService;
import com.quedav1.quedav1back.transaction.application.port.service.GetCurrentUserService;
import com.quedav1.quedav1back.transaction.application.port.service.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
        return new BCryptPasswordEncoderAdapter(
                new BCryptPasswordEncoder()
        );
    }

    @Bean
    public TokenProvider tokenProvider(Environment environment) {

        String secret = environment.getProperty("jwt.secret");

        long expiration = Long.parseLong(
                environment.getProperty("jwt.expiration")
        );

        return new JwtService(secret, expiration);
    }

    @Bean
    public LoginUseCase loginUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenProvider tokenProvider
    ) {
        return new LoginService(
                userRepository,
                passwordEncoder,
                tokenProvider
        );
    }

    @Bean
    public GetCurrentUserUseCase getCurrentUserUseCase(
            UserRepository userRepository
    ) {
        return new GetCurrentUserService(userRepository);
    }
}
