package com.bootcamp.ws.service;

import com.bootcamp.ws.model.Capability;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface CapabilityService {
    Flux<Capability> existsCapabilities(List<Long> req);

    Mono<Boolean> existsByName(String name);

    Mono<Capability> createCapability(Capability request);
}
