package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.infrastructure.inbound.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TechnologyAdapterPort {
    CompletableFuture<Technology> createTechnology(Technology request);
    CompletableFuture<Boolean> existsByName(String name);
    CompletableFuture<List<Technology>> existsTechnologies(List<Long> technologiesIds);
    CompletableFuture<List<Technology>> findTechnologiesByIds(List<Long> technologiesIds);
    CompletableFuture<List<TechnologyCapability>> associateTechnologies(Long capabilityId, List<Long> technologiesIds);


//    Flux<Technology> existsTechnologies(ExistsTechnologiesRequestDto dto);
//    // ------------------ devolver lista de tecnologias (con id y nombre)
//    Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds);
//    // ------------------
//    Mono<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto);
//    Mono<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId);
//    Mono<Boolean> existsByCapabilityId(Long capabilityId);
}
