package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.common.exceptions.ProcessorException;
import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.TechnologyEntityMapper;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyCapabilityRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyRepository;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
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
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)))
                .flatMap(exists -> {
                    if (exists) return Mono.just(true);
                    return Mono.just(false);
                })
                .switchIfEmpty(Mono.error(new ProcessorException("Error checking technology existence", TechnicalMessage.BAD_REQUEST)));
    }

    @Override
    public Mono<Technology> createTechnology(TechnologyCreateRequestDto requestDto) {
        return technologyRepository.existsByName(requestDto.getName())
                .flatMap(exists -> technologyRepository.save(mapper.toEntityFromDto(requestDto))
                        .flatMap(savedTechnology -> Mono.just(mapper.toDomainFromEntity(savedTechnology)))
                        .switchIfEmpty(Mono.error(new ProcessorException("Error saving technology", TechnicalMessage.BAD_REQUEST))));
    }

    @Override
    public Flux<Technology> existsTechnologies(ExistsTechnologiesRequestDto dto) {
        return technologyRepository.findAllById(dto.getTechnologiesIds())
                .flatMap(technologyEntity -> Mono.just(mapper.toDomainFromEntity(technologyEntity)))
                .switchIfEmpty(Mono.error(new ProcessorException("Error fetching technologies", TechnicalMessage.BAD_REQUEST)));
    }

    @Override
    public Mono<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto) {
        return technologyCapabilityRepository.existsByCapabilityId(dto.getCapabilityId())
                .flatMap(exists -> {
                    List<TechnologyCapabilityEntity> entities = dto.getTechnologiesIds().stream()
                            .map(techId -> TechnologyCapabilityEntity.builder()
                                    .technologyId(techId)
                                    .capabilityId(dto.getCapabilityId())
                                    .build())
                            .toList();
                    return mapper.toDomainsFromEntities(technologyCapabilityRepository.saveAll(entities).collectList());
                })
                .switchIfEmpty(Mono.error(new ProcessorException("Error fetching technologies-capabilities", TechnicalMessage.BAD_REQUEST)));
    }

    @Override
    public Mono<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId) {
        return mapper.toMonoTechnologyCapabilityListFromFluxEntities(technologyCapabilityRepository.findAllByCapabilityId(capabilityId))
                .switchIfEmpty(Mono.error(new ProcessorException("Error fetching technologies-capabilities", TechnicalMessage.BAD_REQUEST)));

    }

    @Override
    public Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds) {
        return technologyRepository.findAllById(technologiesIds)
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)))
                .flatMap(technologyEntity -> Mono.just(mapper.toDomainFromEntity(technologyEntity)))
                .switchIfEmpty(Mono.error(new ProcessorException("Error fetching technologies", TechnicalMessage.BAD_REQUEST)));
    }
}
