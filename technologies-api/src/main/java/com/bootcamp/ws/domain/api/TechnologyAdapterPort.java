package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyAdapterPort {
    Mono<Technology> createTechnology(Technology request);
    Mono<Boolean> existsByName(String name);
    Flux<Technology> findTechnologiesByIdIn(List<Long> technologiesIds);
    Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds);
    Flux<TechnologyCapability> associateTechnologies(Long capabilityId, List<Long> technologiesIds);
    Flux<TechnologyCapability> findAllByCapabilityId(Long capabilityId);
    Mono<Boolean> existsByCapabilityId(Long capabilityId);

    Mono<TechnologyCapability> findCapabilityById(Long capabilityId);

    Mono<Boolean> deleteAssocTechnologiesByCapabilityId(Long capabilityId);
}
