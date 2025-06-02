package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.BootcampPersistenceAdapterPort;
import com.bootcamp.ws.domain.spi.BootcampServicePort;

public class BootcampUseCase implements BootcampServicePort {

    private final BootcampPersistenceAdapterPort bootcampPersistence;

    public BootcampUseCase(BootcampPersistenceAdapterPort bootcampPersistence) {
        this.bootcampPersistence = bootcampPersistence;
    }
}
