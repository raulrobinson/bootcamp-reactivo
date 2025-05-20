package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Capability;
import io.github.resilience4j.core.functions.Either;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityPersistenceAdapterPort {
    Flux<Capability> existsCapabilities(List<Long> req);

    Mono<Boolean> existsByName(String name);

    Mono<Capability> createCapability(Capability request);

    Flux<Capability> findAllById(List<Long> req);
}
