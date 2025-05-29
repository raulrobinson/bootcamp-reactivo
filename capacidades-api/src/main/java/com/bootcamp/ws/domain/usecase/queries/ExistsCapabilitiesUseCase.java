package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.CapabilityAdapterPort;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.spi.ExistsCapabilitiesServicePort;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ExistsCapabilitiesUseCase implements ExistsCapabilitiesServicePort {

    private final CapabilityAdapterPort capabilityAdapterPort;

    public ExistsCapabilitiesUseCase(CapabilityAdapterPort capabilityAdapterPort) {
        this.capabilityAdapterPort = capabilityAdapterPort;
    }

    @Override
    public CompletableFuture<Capability> existsCapabilities(List<Long> longs) {
        return null;
    }
}
