package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Capability;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CapabilityPersistenceAdapterPort {
    Flux<Capability> findAllCapabilitiesByIds(List<Long> req);

//    Mono<List<FindAssociatesTechsByCapIdResponseDto>> findAssociatesTechsByCapId(Long capabilityId);
//    Flux<Capability> existsCapabilities(List<Long> req);
//    Mono<Boolean> existsByName(String name);
//    Mono<Capability> createCapability(Capability request);
}
