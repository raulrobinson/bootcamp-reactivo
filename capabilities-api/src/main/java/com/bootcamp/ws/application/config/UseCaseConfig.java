package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.usecase.CapabilityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CapabilityUseCase capabilityUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort) {
        return new CapabilityUseCase(capabilityPersistenceAdapterPort);
    }
}
