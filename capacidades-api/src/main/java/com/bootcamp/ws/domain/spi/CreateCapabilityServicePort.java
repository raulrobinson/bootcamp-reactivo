package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Capability;

import java.util.concurrent.CompletableFuture;

public interface CreateCapabilityServicePort {
    CompletableFuture<Capability> createCapability(Capability requestDto);
}
