package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import com.bootcamp.ws.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyAdapterPort {
    Flux<Technology> existsTechnologies(ExistsTechnologiesDto dto);

    Mono<Technology> createTechnology(TechnologyEntity technologyEntity);

    Mono<List<TechnologyCapabilityEntity>> associateTechnologies(AssociateTechnologiesCreateDto dto);

    Mono<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId);

    Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds);
}
