package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.mapper.TechnologyDomainMapper;
import com.bootcamp.ws.domain.spi.ExistsByNameServicePort;
import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;
import com.bootcamp.ws.infrastructure.common.exceptions.NoContentException;

import java.util.concurrent.CompletableFuture;

public class ExistsByNameServicePortUseCase implements ExistsByNameServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;
    private final TechnologyDomainMapper mapper;

    public ExistsByNameServicePortUseCase(TechnologyAdapterPort technologyAdapterPort, TechnologyDomainMapper mapper) {
        this.technologyAdapterPort = technologyAdapterPort;
        this.mapper = mapper;
    }

    @Override
    public CompletableFuture<Boolean> existsByName(String name) {
        return technologyAdapterPort.existsByName(name)
                .thenApply(exists -> {
                    if (!exists) throw new NoContentException(TechnicalMessage.NO_CONTENT);
                    return true;
                });
    }
}
