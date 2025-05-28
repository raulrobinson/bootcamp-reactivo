package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.CreateTechnologyServicePort;

import java.util.concurrent.CompletableFuture;

public class CreateTechnologyUseCase implements CreateTechnologyServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public CreateTechnologyUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public CompletableFuture<Technology> createTechnology(Technology request) {
        return technologyAdapterPort.existsByName(request.getName())
                .thenCompose(exists -> {
                    if (exists) {
                        return CompletableFuture.failedFuture(new DuplicateResourceException(
                                "Already exists",
                                "TECH_DUPLICATE",
                                request.getName()
                        ));
                    }
                    return technologyAdapterPort.createTechnology(request);
                });
    }
}
