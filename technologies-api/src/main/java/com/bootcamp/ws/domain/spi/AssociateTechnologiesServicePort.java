package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AssociateTechnologiesServicePort {
    Mono<List<TechnologyCapabilityEntity>> associateTechnologies(AssociateTechnologiesCreateDto dto);
}
