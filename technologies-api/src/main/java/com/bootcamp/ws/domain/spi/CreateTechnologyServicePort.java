package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import reactor.core.publisher.Mono;

public interface CreateTechnologyServicePort {
    Mono<Technology> createTechnology(TechnologyEntity technologyEntity);
}
