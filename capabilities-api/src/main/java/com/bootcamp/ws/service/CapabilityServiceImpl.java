package com.bootcamp.ws.service;

import com.bootcamp.ws.common.enums.TechnicalMessage;
import com.bootcamp.ws.common.exceptions.ValidationException;
import com.bootcamp.ws.mapper.CapabilityMapper;
import com.bootcamp.ws.model.Capability;
import com.bootcamp.ws.outbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.outbound.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.repository.CapabilityBootcampRepository;
import com.bootcamp.ws.repository.CapabilityRepository;
import com.bootcamp.ws.outbound.TechnologyExternal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CapabilityServiceImpl implements CapabilityService {

    private final TransactionalOperator tx;

    private final CapabilityRepository capabilityRepository;
    private final CapabilityBootcampRepository capabilityBootcampRepository;

    private final TechnologyExternal technologyExternal;

    private final CapabilityMapper mapper;

    @Override
    public Flux<Capability> existsCapabilities(List<Long> req) {
        return capabilityRepository.findAllById(req).map(mapper::toCapabilityDomainFromEntity);
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
        return technologyExternal.existsTechnologies(technologiesIds)
                .switchIfEmpty(Mono.error(new ValidationException(TechnicalMessage.INVALID_REQUEST)))
                .flatMap(validas -> {

                    System.out.println("validas: " + validas);

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

                                return technologyExternal.associateTechnologies(associateRequest)
                                        .thenReturn(savedCap);
                            })
                            .map(mapper::toCapabilityDomainFromEntity);
                }).as(tx::transactional);

//        return capabilityRepository.save(mapper.toCapabilityEntityFromDomain(request))
//                .map(mapper::toCapabilityDomainFromEntity);
    }

//    public Mono<Capability> createCapability(Capability request) {
//        return capabilityRepository.save(mapper::toCapabilityEntityFromDomain)
//                .map(mapper::toCapabilityDomainFromEntity);
//    }

}
