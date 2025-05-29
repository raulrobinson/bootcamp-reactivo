package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.AssociatesTechsByCapId;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyCapability;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TechnologyAdapterPort {
    CompletableFuture<List<Technology>> existsTechnologies(List<Long> technologiesIds);
    CompletableFuture<List<TechnologyCapability>> associateTechnologies(Long capabilityId, List<Long> technologiesIds);
    CompletableFuture<List<AssociatesTechsByCapId>> findAssociatesTechsByCapId(Long capabilityId);
}
