package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.spi.ExistsTechnologiesServicePort;

public class ExistsTechnologiesUseCase implements ExistsTechnologiesServicePort {

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;

    public ExistsTechnologiesUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort) {
        this.capabilityPersistenceAdapterPort = capabilityPersistenceAdapterPort;
    }
}
