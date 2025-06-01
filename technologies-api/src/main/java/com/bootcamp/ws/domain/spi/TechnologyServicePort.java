package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface TechnologyServicePort {
    Mono<Technology> createTechnology(Technology requestDto);
    Flux<Technology> findTechnologiesByIdIn(List<Long> technologiesIds);
    Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds);
    Mono<Map<Object, Object>> findAssociatesTechsByCapId(Long capabilityId);
    Flux<TechnologyCapability> associateTechnologies(Long capabilityId, List<Long> technologiesIds);
}
