package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.domain.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityBootcampRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityRepository;
import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import com.bootcamp.ws.infrastructure.inbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.CapabilityFullList;
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
    public Flux<Capability> findAllCapabilitiesByIds(List<Long> req) {
        return capabilityRepository.findAllById(req)
                .map(mapper::toCapabilityDomainFromEntity);
    }

//    @Override
//    public Mono<List<FindAssociatesTechsByCapIdResponseDto>> findAssociatesTechsByCapId(Long capabilityId) {
//        return technologyExternalAdapterPort.findAssociatesTechsByCapId(capabilityId);
//                //.map(mapper::toCapabilityWithTechnologiesDtoFromEntity);
//    }

//    @Override
//    public Flux<Capability> existsCapabilities(List<Long> req) {
//        return capabilityRepository.findAllById(req)
//                .map(mapper::toCapabilityDomainFromEntity);
//    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return capabilityRepository.existsByName(name);
    }

    @Override
    public Mono<Capability> createCapability(Capability request) {

        ExistsTechnologiesDto technologiesIds = ExistsTechnologiesDto.builder()
                .technologiesIds(request.getTechnologyIds())
                .build();

        return technologyExternalAdapterPort.findTechnologiesByIdIn(technologiesIds)
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.INVALID_REQUEST)))
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
                })
                .as(tx::transactional);
    }

    public Mono<CapabilityFullList> buildCapabilityResponseDto(Capability capability) {
        return capabilityRepository.findById(capability.getId())
                .flatMap(cap -> {
                    if (cap == null) return Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND));
                    return technologyExternalAdapterPort.findAssociatesTechsByCapId(capability.getId())
                            .flatMap(response -> {
                                if (response == null) return Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND));
                                return Mono.just(CapabilityFullList.builder()
                                        .id(cap.getId())
                                        .name(cap.getName())
                                        .technologies(response.getTechnologies().stream()
                                                .map(item -> Technology.builder()
                                                        .id((long) item.getId())
                                                        .name(item.getName())
                                                        .build())
                                                .toList())
                                        .build());
                            });
                });
    }




    @Override
    public Mono<CapabilityFullList> findCapabilityById(Long id) {
        return capabilityRepository.findById(id)
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND)))
                .flatMap(cap -> buildCapabilityResponseDto(mapper.toCapabilityDomainFromEntity(cap)))
                .onErrorResume(e -> Mono.error(new ProcessorException(TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS, e)));
    }


}
