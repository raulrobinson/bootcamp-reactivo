package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.BusinessException;
import com.bootcamp.ws.domain.dto.request.CapabilityCreateDto;
import com.bootcamp.ws.domain.dto.response.CapabilityResponseDto;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.spi.CreateCapabilityServicePort;
import com.bootcamp.ws.infrastructure.inbound.mapper.CapabilityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCapabilityUseCase implements CreateCapabilityServicePort {

    private final TransactionalOperator tx;

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;
    private final TechnologyExternalAdapterPort technologyExternalAdapterPort;
    private final CapabilityMapper mapper;

    @Override
    public Mono<CapabilityResponseDto> createCapability(CapabilityCreateDto request) {
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

        // Paso 1: Obtener IDs de tecnologías del request
        List<Long> technologyIds = request.getTechnologyIds();
        Capability capabilityRequest = Capability.builder().build();

        return mapper.toDtoFromDomain(capabilityPersistenceAdapterPort.createCapability(request))
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST)));
    }
}
