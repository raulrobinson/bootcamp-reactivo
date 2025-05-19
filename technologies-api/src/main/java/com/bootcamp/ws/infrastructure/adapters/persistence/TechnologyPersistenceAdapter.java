package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.common.exceptions.BusinessException;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
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
    public Flux<Technology> existsTechnologies(ExistsTechnologiesDto dto) {
        return technologyRepository.findAllById(dto.getTechnologiesIds())
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)))
                .flatMap(technologyEntity -> {
                    if (technologyEntity == null) {
                        return Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT));
                    } else {
                        return Mono.just(mapper.toDomainFromEntity(technologyEntity));
                    }
                })
                .doOnError(error -> log.error("Error fetching technology: {}", error.getMessage()));
    }

    @Override
    public Mono<Technology> createTechnology(TechnologyEntity technologyEntity) {
        return technologyRepository.existsByName(technologyEntity.getName())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new BusinessException(TechnicalMessage.ALREADY_EXISTS));
                    } else {
                        return technologyRepository.save(technologyEntity)
                                .flatMap(savedTechnology -> {
                                    if (savedTechnology == null) {
                                        return Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST));
                                    } else {
                                        System.out.println("Saved technology: " + savedTechnology);
                                        return Mono.just(mapper.toDomainFromEntity(savedTechnology));
                                    }
                                })
                                .doOnNext(savedTechnology -> log.info("Saved technology: {}", savedTechnology))
                                .doOnError(error -> log.error("Error saving technology: {}", error.getMessage()));
                    }
                })
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST)));
    }

    @Override
    public Mono<List<TechnologyCapabilityEntity>> associateTechnologies(AssociateTechnologiesCreateDto dto) {
        return technologyCapabilityRepository.existsByCapabilityId(dto.getCapabilityId())
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new BusinessException(TechnicalMessage.ALREADY_EXISTS));
                    } else {
                        List<TechnologyCapabilityEntity> entities = dto.getTechnologiesIds().stream()
                                .map(techId -> TechnologyCapabilityEntity.builder()
                                        .technologyId(techId)
                                        .capabilityId(dto.getCapabilityId())
                                        .build())
                                .toList();
                        return technologyCapabilityRepository.saveAll(entities).collectList();
                    }
                });
    }
}
