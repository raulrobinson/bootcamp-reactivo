package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.FindAllServicePort;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FindAllUseCase implements FindAllServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public FindAllUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public CompletableFuture<List<Technology>> findAll() {
        return technologyAdapterPort.findAll();
    }
}
