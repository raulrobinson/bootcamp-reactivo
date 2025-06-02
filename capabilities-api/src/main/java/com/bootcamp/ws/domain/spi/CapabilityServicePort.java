package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.model.CapabilityFullList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityServicePort {
    Mono<Capability> createCapability(Capability request);

    Flux<CapabilityFullList> findCapabilitiesByIdIn(List<Long> ids);

    Mono<CapabilityFullList> findCapabilityById(Long id);

    Mono<Boolean> deleteCapability(Long capabilityId);
}
