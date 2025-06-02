package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.BootcampPersistenceAdapterPort;
import com.bootcamp.ws.domain.usecase.BootcampUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public BootcampUseCase bootcampUseCase(BootcampPersistenceAdapterPort bootcampPersistence) {
        return new BootcampUseCase(bootcampPersistence);
    }
}
