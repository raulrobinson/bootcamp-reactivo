package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.CreateTechnologyServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateTechnologyUseCase implements CreateTechnologyServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    @Override
    public Mono<Technology> createTechnology(TechnologyEntity technologyEntity) {
        return technologyAdapterPort.createTechnology(technologyEntity)
                .map(technology -> Technology.builder()
                        .id(technology.getId())
                        .name(technology.getName())
                        .description(technology.getDescription())
                        .build());
    }
}
