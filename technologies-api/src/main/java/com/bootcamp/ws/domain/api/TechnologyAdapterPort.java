package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyAdapterPort {
    Mono<Boolean> existsByName(String name);

    Flux<Technology> existsTechnologies(ExistsTechnologiesRequestDto dto);

    Mono<Technology> createTechnology(TechnologyCreateRequestDto technologyEntity);

//    Mono<List<TechnologyCapabilityEntity>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto);
//
//    Mono<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId);

    Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds);
}
