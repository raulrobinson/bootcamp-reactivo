package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.spi.CapabilityServicePort;
import com.bootcamp.ws.domain.model.CapabilityFullList;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class CapabilityUseCase implements CapabilityServicePort {

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;

    public CapabilityUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort) {
        this.capabilityPersistenceAdapterPort = capabilityPersistenceAdapterPort;
    }

    @Override
    public Mono<Capability> createCapability(Capability request) {
        // 1. Check if the capability with the same name already exists
        return capabilityPersistenceAdapterPort.existsByName(request.getName())
                .flatMap(exists -> {
                    if (exists) {
                        // 2. If it exists, return an error
                        return Mono.error(new BusinessException(
                                TechnicalMessage.ALREADY_EXISTS,
                                "CAPABILITY_DUPLICATE"));
                    }
                    // 3. If it does not exist, create the capability
                    return capabilityPersistenceAdapterPort.createCapability(request);
                });
    }

    @Override
    public Flux<CapabilityFullList> findCapabilitiesByIdIn(List<Long> longs) {
        // 1. Check if the list of IDs is empty
        return capabilityPersistenceAdapterPort.findAllCapabilitiesByIds(longs)
                .flatMap(capabilities -> {
                    // 2. If the list is empty, return an error
                    if (capabilities == null) {
                        return Mono.error(new BusinessException(
                                TechnicalMessage.NOT_FOUND,
                                "CAPABILITIES_NOT_FOUND"));
                    }
                    // 3. If the list is not empty, return the capabilities
                    return Flux.just(capabilities);
                });
    }

    @Override
    public Mono<CapabilityFullList> findCapabilityById(Long id) {
        // 1. Check if the capability exists
        return capabilityPersistenceAdapterPort.findCapabilityById(id)
                .flatMap(capability -> {
                    if (capability == null) {
                        // 2. If it does not exist, return an error
                        return Mono.error(new BusinessException(
                                TechnicalMessage.NOT_FOUND,
                                "CAPABILITY_NOT_FOUND"));
                    }
                    // 3. Return the capability if it exists
                    return Mono.just(capability);
                });
    }

    @Override
    public Mono<Boolean> deleteCapability(Long capabilityId) {
        // 1. Check if the capability exists
        return capabilityPersistenceAdapterPort.findCapabilityById(capabilityId)
                .flatMap(capability -> {
                    if (capability == null) {
                        return Mono.error(new BusinessException(
                                TechnicalMessage.NOT_FOUND,
                                "CAPABILITY_NOT_FOUND"));
                    }
                    // 2. Check if the capability has associated technologies
                    return capabilityPersistenceAdapterPort.deleteCapability(capabilityId);
                });
    }
}
