package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyAdapterPort {
    Mono<Technology> createTechnology(TechnologyCreateRequestDto technologyEntity);
    Mono<Boolean> existsByName(String name);
    Flux<Technology> existsTechnologies(ExistsTechnologiesRequestDto dto);
    // ------------------ devolver lista de tecnologias (con id y nombre)
    Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds);
    // ------------------
    Mono<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto);
    Mono<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId);
    Mono<Boolean> existsByCapabilityId(Long capabilityId);
}
