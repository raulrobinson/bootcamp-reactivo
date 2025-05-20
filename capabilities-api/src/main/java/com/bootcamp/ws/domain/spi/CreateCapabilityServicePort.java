package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Capability;
import reactor.core.publisher.Mono;

public interface CreateCapabilityServicePort {
    Mono<Capability> createCapability(Capability request);
}
