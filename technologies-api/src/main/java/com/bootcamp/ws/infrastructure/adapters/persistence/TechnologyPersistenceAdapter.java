package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import com.bootcamp.ws.infrastructure.common.exception.TechnicalException;
import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.TechnologyEntityMapper;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyCapabilityRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyRepository;
import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class TechnologyPersistenceAdapter implements TechnologyAdapterPort {

    private final TechnologyRepository technologyRepository;
    private final TechnologyCapabilityRepository technologyCapabilityRepository;

    private final TechnologyEntityMapper mapper;

    @Override
    public CompletableFuture<Boolean> existsByName(String name) {
        return technologyRepository.existsByName(name)
                .switchIfEmpty(Mono.error(new TechnicalException(TechnicalMessage.NOT_FOUND)))
                .onErrorMap(e -> new ProcessorException(TechnicalMessage.BAD_REQUEST, e))
                .toFuture();
    }

    @Override
    public CompletableFuture<Technology> createTechnology(Technology requestDto) {
        return technologyRepository.existsByName(requestDto.getName())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new TechnicalException(TechnicalMessage.ALREADY_EXISTS));
                    }
                    return technologyRepository.save(mapper.toEntityFromDomain(requestDto));
                })
                .map(mapper::toDomainFromEntity)
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST)))
                .toFuture();
    }

    @Override
    public CompletableFuture<List<Technology>> existsTechnologies(List<Long> technologiesIds) {
        return technologyRepository.findAllById(technologiesIds)
                .map(mapper::toDomainFromEntity)
                .collectList()
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST)))
                .toFuture();
    }

    @Override
    public CompletableFuture<List<Technology>> findTechnologiesByIds(List<Long> technologiesIds) {
        return technologyRepository.findAllById(technologiesIds)
                .map(mapper::toDomainFromEntity)
                .collectList()
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST)))
                .toFuture();
    }

    @Override
    public CompletableFuture<List<TechnologyCapability>> associateTechnologies(Long capabilityId, List<Long> technologiesIds) {
        List<TechnologyCapability> domains = technologiesIds.stream()
                .map(techId -> new TechnologyCapability.Builder()
                        .technologyId(techId)
                        .capabilityId(capabilityId)
                        .build())
                .toList();

        return technologyCapabilityRepository
                .saveAll(mapper.toTechnologyCapabilityEntitiesFromDomains(domains))
                .collectList()
                .map(mapper::toTechnologyCapabilityDomainsFromEntities) // convertir directo sin usar Mono
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST)))
                .toFuture();
    }

    @Override
    public CompletableFuture<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId) {
        return mapper.toMonoTechnologyCapabilityListFromFluxEntities(technologyCapabilityRepository.findAllByCapabilityId(capabilityId))
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST)))
                .toFuture();
    }
}
