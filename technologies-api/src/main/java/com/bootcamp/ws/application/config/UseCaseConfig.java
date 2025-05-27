package com.bootcamp.ws.application.config;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.usecase.commands.CreateTechnologyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UseCaseConfig {

    @Bean
    public CreateTechnologyUseCase createTechnologyUseCase(TechnologyAdapterPort technologyAdapterPort) {
        return new CreateTechnologyUseCase(technologyAdapterPort);
    }

//    @Bean
//    public ExistsTechnologiesUseCase existsTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort,
//                                                               TechnologyDomainMapper mapper) {
//        return new ExistsTechnologiesUseCase(technologyAdapterPort, mapper);
//    }
//
//    @Bean
//    public FindTechnologiesByIdsUseCase findTechnologiesByIdsUseCase(TechnologyAdapterPort technologyAdapterPort,
//                                                                     TechnologyDomainMapper mapper) {
//        return new FindTechnologiesByIdsUseCase(technologyAdapterPort, mapper);
//    }
//
//    @Bean
//    public AssociateTechnologiesUseCase associateTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
//        return new AssociateTechnologiesUseCase(technologyAdapterPort);
//    }
//
//    @Bean
//    public FindAssociatesTechsByCapIdUseCase findAssociatesTechsByCapIdUseCase(TechnologyAdapterPort technologyAdapterPort) {
//        return new FindAssociatesTechsByCapIdUseCase(technologyAdapterPort);
//    }
}
