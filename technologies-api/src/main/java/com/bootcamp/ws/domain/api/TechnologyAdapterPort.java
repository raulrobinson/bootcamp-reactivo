package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.model.Technology;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TechnologyAdapterPort {
    CompletableFuture<Technology> createTechnology(Technology request);
    CompletableFuture<Boolean> existsByName(String name);
    CompletableFuture<List<Technology>> existsTechnologies(List<Long> technologiesIds);
    CompletableFuture<List<Technology>> findTechnologiesByIds(List<Long> technologiesIds);
    CompletableFuture<List<TechnologyCapability>> associateTechnologies(Long capabilityId, List<Long> technologiesIds);
    CompletableFuture<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId);
    CompletableFuture<Boolean> existsByCapabilityId(Long capabilityId);
}
