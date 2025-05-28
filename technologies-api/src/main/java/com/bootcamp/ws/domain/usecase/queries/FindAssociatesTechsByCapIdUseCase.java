package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.spi.FindAssociatesTechsByCapIdServicePort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class FindAssociatesTechsByCapIdUseCase implements FindAssociatesTechsByCapIdServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public FindAssociatesTechsByCapIdUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public CompletableFuture<Map<Object, Object>> findAssociatesTechsByCapId(Long capabilityId) {
        return technologyAdapterPort.findAllByCapabilityId(capabilityId)
                .thenCompose(techCaps -> {
                    if (techCaps == null || techCaps.isEmpty()) {
                        return CompletableFuture.failedFuture(
                                new BusinessException("No technologies associated with the given capability ID.")
                        );
                    }

                    boolean allMatch = techCaps.stream()
                            .allMatch(tc -> tc.getCapabilityId().equals(capabilityId));

                    if (!allMatch) {
                        return CompletableFuture.failedFuture(
                                new BusinessException("All technology capabilities must match the provided capability ID.")
                        );
                    }

                    List<Long> techIds = techCaps.stream()
                            .map(TechnologyCapability::getTechnologyId)
                            .distinct()
                            .toList();

                    return technologyAdapterPort.findTechnologiesByIds(techIds)
                            .thenApply(technologies -> {
                                List<Map<String, Object>> techList = technologies.stream()
                                        .map(tech -> {
                                            Map<String, Object> map = new HashMap<>();
                                            map.put("id",tech.getId());
                                            map.put("name", tech.getName());
                                            return map;
                                        })
                                        .toList();

                                Map<Object, Object> result = new HashMap<>();
                                result.put("capability", capabilityId);
                                result.put("technologies", techList);
                                return result;
                            });
                });
    }
}
