package com.bootcamp.ws.domain.usecase.commands;

public class CreateCapabilityUseCase {

//    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;
//    private final TechnologyExternalAdapterPort technologyExternalAdapterPort;
//    private final CapabilityDomainMapper mapper;
//
//    public CreateCapabilityUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort, TechnologyExternalAdapterPort technologyExternalAdapterPort, CapabilityDomainMapper mapper) {
//        this.capabilityPersistenceAdapterPort = capabilityPersistenceAdapterPort;
//        this.technologyExternalAdapterPort = technologyExternalAdapterPort;
//        this.mapper = mapper;
//    }
//
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
