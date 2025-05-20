package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.ValidationException;
import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.adapters.outbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.infrastructure.adapters.outbound.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityBootcampRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityRepository;
import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapabilityPersistenceAdapter implements CapabilityPersistenceAdapterPort {

    private final TransactionalOperator tx;

    private final CapabilityRepository capabilityRepository;
    private final CapabilityBootcampRepository capabilityBootcampRepository;

    private final TechnologyExternalAdapterPort technologyExternalAdapterPort;

    private final CapabilityEntityMapper mapper;

    @Override
    public Flux<Capability> existsCapabilities(List<Long> req) {
        return capabilityRepository.findAllById(req)
                .map(mapper::toCapabilityDomainFromEntity);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return capabilityRepository.existsByName(name);
    }

    @Override
    public Mono<Capability> createCapability(Capability request) {

        ExistsTechnologiesDto technologiesIds = ExistsTechnologiesDto.builder()
                .technologiesIds(request.getTechnologyIds())
                .build();

        return technologyExternalAdapterPort.existsTechnologies(technologiesIds)
                .switchIfEmpty(Mono.error(new ValidationException(TechnicalMessage.INVALID_REQUEST)))
                .flatMap(validas -> {

                    // Paso 2: Crear entidad de dominio
                    Capability capability = Capability.builder()
                            .name(request.getName())
                            .description(request.getDescription())
                            .technologyIds(request.getTechnologyIds())
                            .build();

                    // Paso 3: Guardar la capacidad
                    return capabilityRepository.save(mapper.toCapabilityEntityFromDomain(capability))
                            .flatMap(savedCap -> {

                                // Paso 4: Asociar tecnologías con la capacidad
                                TechnologyAssociateTechnologies associateRequest = TechnologyAssociateTechnologies.builder()
                                        .capabilityId(savedCap.getId())
                                        .technologiesIds(request.getTechnologyIds())
                                        .build();

                                return technologyExternalAdapterPort.associateTechnologies(associateRequest)
                                        .thenReturn(savedCap);
                            })
                            .map(mapper::toCapabilityDomainFromEntity);
                }).as(tx::transactional);

//        return capabilityRepository.save(mapper.toCapabilityEntityFromDomain(request))
//                .map(mapper::toCapabilityDomainFromEntity);
    }

    @Override
    public Flux<Capability> findAllById(List<Long> req) {
        return capabilityRepository.findAllById(req)
                .map(mapper::toCapabilityDomainFromEntity);
    }

}
