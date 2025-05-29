package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.ExistsTechnologiesServicePort;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ExistsTechnologiesUseCase implements ExistsTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public ExistsTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public CompletableFuture<List<Technology>> existsTechnologies(List<Long> technologiesIds) {
        return technologyAdapterPort.existsTechnologies(technologiesIds)
                .thenCompose(technologies -> {
                    if (technologies.isEmpty()) {
                        return CompletableFuture.failedFuture(new BusinessException(TechnicalMessage.NOT_FOUND, "No technologies found for the provided IDs."));
                    }
                    return CompletableFuture.completedFuture(technologies);
                });
    }
}
