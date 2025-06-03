package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.PersonPersistencePort;
import com.bootcamp.ws.domain.usecase.PersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public PersonUseCase personUseCase(PersonPersistencePort personPersistence) {
        return new PersonUseCase(personPersistence);
    }
}
