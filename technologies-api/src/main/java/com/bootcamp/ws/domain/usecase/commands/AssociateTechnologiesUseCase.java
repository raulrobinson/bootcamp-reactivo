package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.exception.InvalidValueException;
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
        return technologyAdapterPort.existsByCapabilityId(capabilityId)
                .thenCompose(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return CompletableFuture.failedFuture(new DuplicateResourceException(
                                "Already exists",
                                "DUPLICATE_TECHNOLOGY_CAPABILITY",
                                String.valueOf(capabilityId)));
                    }

                    return technologyAdapterPort.findTechnologiesByIds(technologiesIds)
                            .thenCompose(techs -> {
                                if (techs.isEmpty()) {
                                    return CompletableFuture.failedFuture(new InvalidValueException(
                                            "Invalid value",
                                            "No technologies found for the provided IDs"));
                                }

                                List<TechnologyCapability> domains = techs.stream()
                                        .map(tech -> new TechnologyCapability.Builder()
                                                .technologyId(tech.getId())
                                                .capabilityId(capabilityId)
                                                .build())
                                        .toList();

                                List<Long> techIds = domains.stream()
                                        .map(TechnologyCapability::getTechnologyId)
                                        .toList();

                                return technologyAdapterPort.associateTechnologies(capabilityId, techIds);
                            });
                });
    }
}
