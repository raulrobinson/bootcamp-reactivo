package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.model.Technology;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface TechnologyAdapterPort {
    CompletableFuture<List<Technology>> findAll();

    CompletableFuture<Boolean> existsByName(String name);
//    Optional<Technology> createTechnology(TechnologyCreateRequestDto technologyEntity);
//    CompletableFuture<Boolean> existsByName(String name);
//    List<Technology> existsTechnologies(ExistsTechnologiesRequestDto dto);
//    // ------------------ devolver lista de tecnologias (con id y nombre)
//    List<Technology> findTechnologiesByIds(List<Long> technologiesIds);
//    // ------------------
//    Optional<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto);
//    Optional<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId);
//    Optional<Boolean> existsByCapabilityId(Long capabilityId);
}
