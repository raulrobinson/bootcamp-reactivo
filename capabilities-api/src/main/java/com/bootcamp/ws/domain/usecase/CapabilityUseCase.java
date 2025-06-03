package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.CapabilityServicePort;
import com.bootcamp.ws.domain.model.CapabilityFullList;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public Mono<List<CapabilityFullList>> findCapabilitiesByIdIn(List<Long> ids,
                                                         String sortBy,
                                                         String direction,
                                                         int page,
                                                         int size) {
        // 1. Check if the list of IDs is empty
        return capabilityPersistenceAdapterPort.findAllCapabilitiesByIds(ids)
                //.collectList()
                .flatMap(capabilities -> {
                    // 2. If the list is empty, return an error
                    if (capabilities.isEmpty()) {
                        return Mono.error(new BusinessException(
                                TechnicalMessage.NOT_FOUND,
                                "CAPABILITIES_NOT_FOUND"));
                    }
                    // 3. If the list is not empty, return the capabilities
                    return toDtoListFromDomain(capabilities);
                });
//        return capabilityPersistenceAdapterPort.findByIdsWithPaginationAndSorting(ids, sortBy, direction, page, size)
//                .flatMap(capabilities -> {
//                    // 2. If the list is empty, return an error
//                    if (capabilities == null) {
//                        return Mono.error(new BusinessException(
//                                TechnicalMessage.NOT_FOUND,
//                                "CAPABILITIES_NOT_FOUND"));
//                    }
//                    // 3. If the list is not empty, return the capabilities
//                    return Flux.just(capabilities);
//                    return Flux.fromIterable(capabilities);
//                    return toDtoListFromDomain(Flux.fromIterable(Collections.singleton(capabilities)));
//
//                    return toDtoListFromDomain(capabilities);
//                });
    }

    private Mono<List<CapabilityFullList>> toDtoListFromDomain(List<CapabilityFullList> capabilities) {
        // Convert the list of CapabilityFullList to a list of CapabilityFullList DTOs
        List<CapabilityFullList> dtoList = capabilities.stream()
                .map(capability -> CapabilityFullList.builder()
                        .id(capability.getId())
                        .name(capability.getName())
                        .description(capability.getDescription())
                        .technologies(capability.getTechnologies())
                        .build())
                .collect(Collectors.toList());
        return Mono.just(dtoList);
    }

//    public Mono<List<CapabilityFullList>> toDtoListFromDomain(List<Capability> domain) {
//        List<CapabilityFullList> dtoList = domain.stream()
//                .map(this::toDtoFromEntity)
//                .collect(Collectors.toList());
//        return Mono.just(dtoList);
//    }
//
//    private CapabilityFullList toDtoFromEntity(Capability entity) {
//        return CapabilityFullList.builder()
//                .id(entity.getId())
//                .name(entity.getName())
//                .description(entity.getDescription())
//                .build();
//    }


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
