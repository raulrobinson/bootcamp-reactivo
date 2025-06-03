package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.*;
import com.bootcamp.ws.domain.usecase.ReportUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ReportUseCase reportUseCase(ReportPersistencePort reportPersistence,
                                       BootcampExternalPort bootcampExternalPort,
                                       CapabilityExternalPort capabilityExternalPort,
                                       TechnologyExternalPort technologyExternalPort,
                                       PersonExternalPort personExternalPort) {
        return new ReportUseCase(reportPersistence,
                                 bootcampExternalPort,
                                 capabilityExternalPort,
                                 technologyExternalPort,
                                 personExternalPort);
    }
}
