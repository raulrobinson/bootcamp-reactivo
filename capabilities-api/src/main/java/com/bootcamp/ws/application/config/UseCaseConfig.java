package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.domain.mapper.CapabilityDomainMapper;
import com.bootcamp.ws.domain.usecase.commands.AssociateTechnologiesUseCase;
import com.bootcamp.ws.domain.usecase.commands.CreateCapabilityUseCase;
import com.bootcamp.ws.domain.usecase.queries.ExistsCapabilitiesUseCase;
import com.bootcamp.ws.domain.usecase.queries.ExistsTechnologiesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public AssociateTechnologiesUseCase associateTechnologiesUseCase(TechnologyExternalAdapterPort technologyExternalAdapterPort) {
        return new AssociateTechnologiesUseCase(technologyExternalAdapterPort);
    }

    @Bean
    public CreateCapabilityUseCase createCapabilityUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort,
                                                           TechnologyExternalAdapterPort technologyExternalAdapterPort,
                                                           CapabilityDomainMapper mapper) {
        return new CreateCapabilityUseCase(capabilityPersistenceAdapterPort, technologyExternalAdapterPort, mapper);
    }

    @Bean
    public ExistsCapabilitiesUseCase existsCapabilitiesUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort,
                                                               CapabilityDomainMapper mapper) {
        return new ExistsCapabilitiesUseCase(capabilityPersistenceAdapterPort, mapper);
    }

    @Bean
    public ExistsTechnologiesUseCase existsTechnologiesUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort) {
        return new ExistsTechnologiesUseCase(capabilityPersistenceAdapterPort);
    }
}
