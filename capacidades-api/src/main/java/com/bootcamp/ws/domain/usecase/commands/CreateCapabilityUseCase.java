package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.CapabilityAdapterPort;
import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.CreateCapabilityServicePort;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CreateCapabilityUseCase implements CreateCapabilityServicePort {

    private final CapabilityAdapterPort capabilityAdapterPort;
    private final TechnologyAdapterPort technologyAdapterPort;

    public CreateCapabilityUseCase(CapabilityAdapterPort capabilityAdapterPort, TechnologyAdapterPort technologyAdapterPort) {
        this.capabilityAdapterPort = capabilityAdapterPort;
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public CompletableFuture<Capability> createCapability(Capability requestDto) {
        return technologyAdapterPort.existsTechnologies(requestDto.getTechnologies().stream().map(Technology::getId).toList())
                .thenCompose(validas -> {
                    if (validas.isEmpty()) {
                        return CompletableFuture.failedFuture(new DuplicateResourceException(
                                "Already exists",
                                "INVALID_TECHNOLOGY_IDS",
                                requestDto.getTechnologies().toString()
                        ));
                    }

                    List<Technology> technologies = validas.stream()
                            .map(tech -> Technology.builder()
                                    .id(tech.getId())
                                    .name(tech.getName())
                                    .build())
                            .toList();

                    // Paso 2: Crear entidad de dominio
                    Capability capability = Capability.builder()
                            .name(requestDto.getName())
                            .description(requestDto.getDescription())
                            .technologies(technologies)
                            .build();

                    // Paso 3: Guardar la capacidad
                    return capabilityAdapterPort.createCapability(capability)
                            .thenCompose(savedCap -> {
                                // Paso 4: Asociar tecnologías con la capacidad
                                return technologyAdapterPort.associateTechnologies(savedCap.getId(), requestDto.getTechnologies().stream().map(Technology::getId).toList())
                                        .thenApply(techCapabilities -> savedCap);
                            });
                });
    }

//    @Override
//    public Mono<CapabilityResponseDto> createCapability(CapabilityCreateDto request) {
//
//        return null;

//        ExistsTechnologiesDto technologiesIds = ExistsTechnologiesDto.builder()
//                .technologiesIds(request.getTechnologyIds())
//                .build();
//        return technologyExternalAdapterPort.existsTechnologies(technologiesIds)
//                .switchIfEmpty(Mono.error(new ValidationException(TechnicalMessage.INVALID_REQUEST)))
//                .flatMap(validas -> {
//
//                    System.out.println("validas: " + validas);
//
//                    // Paso 2: Crear entidad de dominio
//                    Capability capability = Capability.builder()
//                            .name(request.getName())
//                            .description(request.getDescription())
//                            .technologyIds(request.getTechnologyIds())
//                            .build();
//
//                    // Paso 3: Guardar la capacidad
//                    return createCapability(capability)
//                            .flatMap(savedCap -> {
//                                // Paso 4: Asociar tecnologías con la capacidad
//                                TechnologyAssociateTechnologies associateRequest = TechnologyAssociateTechnologies.builder()
//                                        .capabilityId(savedCap.getId())
//                                        .technologiesIds(request.getTechnologyIds())
//                                        .build();
//
//                                return technologyExternalAdapterPort.associateTechnologies(associateRequest)
//                                        .thenReturn(savedCap);
//                            });
//                            //.map(mapper::toCapabilityDomainFromEntity);
//                }).as(tx::transactional);

//        // Paso 1: Obtener IDs de tecnologías del request
//        List<Long> technologyIds = request.getTechnologyIds();
//        Capability capabilityRequest = Capability.builder().build();
//
//        return mapper.toDtoFromDomain(capabilityPersistenceAdapterPort.createCapability(request))
//                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST)));
//    }
}
