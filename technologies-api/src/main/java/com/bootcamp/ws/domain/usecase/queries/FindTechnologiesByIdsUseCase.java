package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.FindTechnologiesByIdsServicePort;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class FindTechnologiesByIdsUseCase implements FindTechnologiesByIdsServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public FindTechnologiesByIdsUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public CompletableFuture<List<Technology>> findTechnologiesByIds(List<Long> technologiesIds) {
        return technologyAdapterPort.findTechnologiesByIds(technologiesIds)
                .thenCompose(technologies -> {
                    if (technologies == null || technologies.isEmpty()) {
                        return CompletableFuture.failedFuture(new BusinessException(
                                "No technologies found for the provided IDs",
                                "TECHNOLOGIES_NOT_FOUND",
                                technologiesIds.toString()));
                    }
                    return CompletableFuture.completedFuture(technologies);
                });
    }
}
