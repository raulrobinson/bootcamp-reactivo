package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.TechnicalMessage;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.TechnologyEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyCapabilityRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyRepository;
import com.bootcamp.ws.infrastructure.common.exception.DatabaseResourceException;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import com.bootcamp.ws.infrastructure.common.exception.TechnicalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechnologyPersistenceAdapter implements TechnologyAdapterPort {

    private final TechnologyRepository technologyRepository;
    private final TechnologyCapabilityRepository technologyCapabilityRepository;
    private final TechnologyEntityMapper mapper;

    @Override
    public Mono<Boolean> existsByName(String name) {
        return technologyRepository.existsByName(name)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalMessage.NOT_FOUND)))
                .onErrorMap(e -> {
                    if (e instanceof TechnicalException || e instanceof DatabaseResourceException) {
                        return e;
                    }
                    return new DatabaseResourceException("Error accessing database", e);
                });
    }

    @Override
    public Mono<Technology> createTechnology(Technology requestDto) {
        return technologyRepository.save(mapper.toEntityFromDomain(requestDto))
                .map(mapper::toDomainFromEntity)
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST)))
                .onErrorMap(e -> {
                    if (e instanceof TechnicalException || e instanceof DatabaseResourceException) {
                        return e;
                    }
                    return new DatabaseResourceException("Error accessing database", e);
                });
    }

    @Override
    public Flux<Technology> findTechnologiesByIdIn(List<Long> technologiesIds) {
        return technologyRepository.findAllById(technologiesIds)
                .map(mapper::toDomainFromEntity)
                .flatMap(tech -> {
                    if (tech == null) {
                        return Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST));
                    }
                    return Mono.just(tech);
                });
    }

    @Override
    public Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds) {
        return technologyRepository.findAllById(technologiesIds)
                .map(mapper::toDomainFromEntity)
                .flatMap(tech -> {
                    if (tech == null) {
                        return Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST));
                    }
                    return Mono.just(tech);
                });
    }

    @Override
    public Flux<TechnologyCapability> associateTechnologies(Long capabilityId, List<Long> technologiesIds) {
        List<TechnologyCapability> domains = technologiesIds.stream()
                .map(techId -> TechnologyCapability.builder()
                        .technologyId(techId)
                        .capabilityId(capabilityId)
                        .build())
                .toList();

        return technologyCapabilityRepository
                .saveAll(mapper.toTechnologyCapabilityEntitiesFromDomains(domains))
                .flatMap(entity -> Mono.just(TechnologyCapability.builder()
                        .technologyId(entity.getTechnologyId())
                        .capabilityId(entity.getCapabilityId())
                        .build()));
    }

    @Override
    public Flux<TechnologyCapability> findAllByCapabilityId(Long capabilityId) {
        return mapper.toMonoTechnologyCapabilityListFromFluxEntities(
                        technologyCapabilityRepository.findAllByCapabilityId(capabilityId))
                .flatMapMany(Flux::fromIterable);
    }

    @Override
    public Mono<Boolean> existsByCapabilityId(Long capabilityId) {
        return technologyCapabilityRepository.existsByCapabilityId(capabilityId)
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.NOT_FOUND)));
    }
}
