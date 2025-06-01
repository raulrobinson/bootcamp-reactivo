package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.usecase.TechnologyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    @Bean
    public TechnologyUseCase technologyUseCase(TechnologyAdapterPort technologyAdapterPort) {
        return new TechnologyUseCase(technologyAdapterPort);
    }
}
