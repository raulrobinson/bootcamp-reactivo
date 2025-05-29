package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.CapabilityAdapterPort;
import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.usecase.commands.AssociateTechnologiesUseCase;
import com.bootcamp.ws.domain.usecase.commands.CreateCapabilityUseCase;
import com.bootcamp.ws.domain.usecase.queries.ExistsCapabilitiesUseCase;
import com.bootcamp.ws.domain.usecase.queries.ExistsTechnologiesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

@Configuration
@EnableR2dbcAuditing
public class UseCaseConfig {

    @Bean
    public ExistsTechnologiesUseCase existsTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        return new ExistsTechnologiesUseCase(technologyAdapterPort);
    }

    @Bean
    public ExistsCapabilitiesUseCase existsCapabilitiesUseCase(CapabilityAdapterPort capabilityAdapterPort) {
        return new ExistsCapabilitiesUseCase(capabilityAdapterPort);
    }

    @Bean
    public AssociateTechnologiesUseCase associateTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        return new AssociateTechnologiesUseCase(technologyAdapterPort);
    }

    @Bean
    public CreateCapabilityUseCase createCapabilityUseCase(CapabilityAdapterPort capabilityAdapterPort,
                                                           TechnologyAdapterPort technologyAdapterPort) {
        return new CreateCapabilityUseCase(capabilityAdapterPort, technologyAdapterPort);
    }
}
