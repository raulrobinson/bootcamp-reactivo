package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.infrastructure.inbound.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface AssociateTechnologiesServicePort {
    CompletableFuture<List<TechnologyCapability>> associateTechnologies(Long capabilityId, List<Long> technologiesIds);
//    Mono<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto);
}
