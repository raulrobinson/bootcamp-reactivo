package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.dto.ExistsTechnologies;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.domain.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityBootcampRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityRepository;
import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
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
    public Flux<CapabilityFullList> findAllCapabilitiesByIds(List<Long> req) {
        // 1. Iterate over the list of IDs and find each capability by ID
        return Flux.fromIterable(req)
                .flatMap(this::findCapabilityById);
    }

    @Override
    public Mono<Boolean> existsByName(String name) {
        return capabilityRepository.existsByName(name);
    }

    @Override
    public Mono<Capability> createCapability(Capability request) {
        // Paso 1: Buscar las tecnologías por ID
        return technologyExternalAdapterPort.findTechnologiesByIdIn(ExistsTechnologies.builder()
                        .technologiesIds(request.getTechnologyIds())
                        .build())
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.INVALID_REQUEST)))
                .flatMap(validas -> {
                    // Paso 2: Guardar la capacidad con la entidad de dominio
                    return capabilityRepository.save(mapper.toCapabilityEntityFromDomain(Capability.builder()
                                    .name(request.getName())
                                    .description(request.getDescription())
                                    .technologyIds(request.getTechnologyIds())
                                    .build()))
                            .flatMap(savedCap -> {
                                // Paso 3: Asociar tecnologías con la capacidad
                                return technologyExternalAdapterPort.associateTechnologies(TechnologyAssociateTechnologies.builder()
                                                .capabilityId(savedCap.getId())
                                                .technologiesIds(request.getTechnologyIds())
                                                .build())
                                        .thenReturn(savedCap);
                            })
                            .map(mapper::toCapabilityDomainFromEntity);
                })
                .as(tx::transactional); // 4. Asegurar que la transacción se maneje correctamente
    }

    @Override
    public Mono<CapabilityFullList> findCapabilityById(Long id) {
        // 1. Buscar la capacidad por ID
        return capabilityRepository.findById(id)
                .flatMap(cap -> {
                    if (cap == null) return Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND));

                    // 2. Buscar las tecnologías asociadas a la capacidad
                    return technologyExternalAdapterPort.findAssociatesTechsByCapId(id)
                            .flatMap(response -> {
                                if (response == null) return Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND));
                                // 3. Mapear la respuesta a CapabilityFullList
                                return Mono.just(CapabilityFullList.builder()
                                        .id(cap.getId())
                                        .name(cap.getName())
                                        .description(cap.getDescription())
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
    public Mono<Boolean> deleteCapability(Long capabilityId) {
        // 1. Buscar la capacidad por ID
        return capabilityRepository.findById(capabilityId)
                .flatMap(cap -> {
                    if (cap == null) return Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND));
                    // 2. Eliminar las tecnologías asociadas a la capacidad
                    return technologyExternalAdapterPort.deleteAssocTechnologiesByCapabilityId(capabilityId)
                            .thenReturn(true);
                })
                .flatMap(deleted -> {
                    if (!deleted) return Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND));
                    // 3. Eliminar la capacidad
                    return capabilityRepository.deleteById(capabilityId)
                            .thenReturn(true);
                })
                .as(tx::transactional); // 4. Asegurar que la transacción se maneje correctamente
    }

}
