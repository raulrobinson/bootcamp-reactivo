package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.spi.CapabilityServicePort;
import com.bootcamp.ws.domain.model.CapabilityFullList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CapabilityUseCase implements CapabilityServicePort {

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;
    private final TechnologyExternalAdapterPort technologyExternalAdapterPort;

    public CapabilityUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort, TechnologyExternalAdapterPort technologyExternalAdapterPort) {
        this.capabilityPersistenceAdapterPort = capabilityPersistenceAdapterPort;
        this.technologyExternalAdapterPort = technologyExternalAdapterPort;
    }

    @Override
    public Mono<Capability> createCapability(Capability request) {
        return capabilityPersistenceAdapterPort.existsByName(request.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new BusinessException(
                                "Capability already exists",
                                "CAPABILITY_DUPLICATE",
                                request.getName()));
                    }
                    return capabilityPersistenceAdapterPort.createCapability(request);
                });
    }

    @Override
    public Flux<CapabilityFullList> findCapabilitiesByIdIn(List<Long> longs) {
        return capabilityPersistenceAdapterPort.findAllCapabilitiesByIds(longs)
                .flatMap(capabilities -> {
                    if (capabilities == null) {
                        return Mono.error(new BusinessException(
                                "No capabilities found for the provided IDs",
                                "CAPABILITIES_NOT_FOUND",
                                longs.toString()));
                    }
                    return Flux.just(capabilities);
                });
    }

    @Override
    public Mono<CapabilityFullList> findCapabilityById(Long id) {
        return capabilityPersistenceAdapterPort.findCapabilityById(id)
                .flatMap(capability -> {
                    if (capability == null) {
                        return Mono.error(new BusinessException(
                                "Capability not found",
                                "CAPABILITY_NOT_FOUND",
                                id.toString()));
                    }
                    return Mono.just(capability);
                });
    }

    @Override
    public Mono<Boolean> deleteCapability(Long capabilityId) {
        return capabilityPersistenceAdapterPort.findCapabilityById(capabilityId)
                .flatMap(capability -> {
                    if (capability == null) {
                        return Mono.error(new BusinessException(
                                "Capability not found",
                                "CAPABILITY_NOT_FOUND",
                                capabilityId.toString()));
                    }
                    // TODO: Implement logic to delete associated technologies
                    return capabilityPersistenceAdapterPort.deleteCapability(capabilityId);
//                    return technologyExternalAdapterPort.deleteAssocTechnologiesByCapabilityId(capabilityId)
//                            .then(capabilityPersistenceAdapterPort.deleteCapability(capabilityId));
                });
    }
}
