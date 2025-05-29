package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public AssociateTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public CompletableFuture<List<TechnologyCapability>> associateTechnologies(Long capabilityId, List<Long> technologiesIds) {
        return technologyAdapterPort.associateTechnologies(capabilityId, technologiesIds)
                .thenCompose(
                        technologyCapabilities -> {
                            if (technologyCapabilities.isEmpty()) {
                                return CompletableFuture.failedFuture(new BusinessException(TechnicalMessage.NOT_FOUND, "No technologies associated"));
                            }
                            return CompletableFuture.completedFuture(technologyCapabilities);
                        }
                );
    }
}
