package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.model.Capability;

import java.util.concurrent.CompletionStage;

public interface CapabilityAdapterPort {
    CompletionStage<Capability> createCapability(Capability capability);
}
