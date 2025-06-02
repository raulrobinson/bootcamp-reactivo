package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.domain.usecase.CapabilityUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CapabilityUseCase capabilityUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort,
                                               TechnologyExternalAdapterPort technologyExternalAdapterPort) {
        return new CapabilityUseCase(capabilityPersistenceAdapterPort, technologyExternalAdapterPort);
    }

//    @Bean
//    public AssociateTechnologiesUseCase associateTechnologiesUseCase(TechnologyExternalAdapterPort technologyExternalAdapterPort) {
//        return new AssociateTechnologiesUseCase(technologyExternalAdapterPort);
//    }
//
//    @Bean
//    public CreateCapabilityUseCase createCapabilityUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort,
//                                                           TechnologyExternalAdapterPort technologyExternalAdapterPort,
//                                                           CapabilityDomainMapper mapper) {
//        return new CreateCapabilityUseCase(capabilityPersistenceAdapterPort, technologyExternalAdapterPort, mapper);
//    }
//
//    @Bean
//    public ExistsCapabilitiesUseCase existsCapabilitiesUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort,
//                                                               CapabilityDomainMapper mapper) {
//        return new ExistsCapabilitiesUseCase(capabilityPersistenceAdapterPort, mapper);
//    }
//
//    @Bean
//    public ExistsTechnologiesUseCase existsTechnologiesUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort) {
//        return new ExistsTechnologiesUseCase(capabilityPersistenceAdapterPort);
//    }
}
