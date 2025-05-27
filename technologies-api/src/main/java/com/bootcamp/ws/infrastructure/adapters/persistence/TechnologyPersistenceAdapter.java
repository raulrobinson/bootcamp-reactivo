package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.bootcamp.ws.infrastructure.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.TechnologyEntityMapper;
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
    public CompletableFuture<List<Technology>> findAll() {
        return technologyRepository.findAll()           // Flux<Technology>
                .map(this::toDomain)
                .collectList()       // Mono<List<Technology>>
                .toFuture();         // CompletableFuture<List<Technology>>
    }

    public Technology toDomain(TechnologyEntity entity) {
        return new Technology.Builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(String.valueOf(entity.getCreatedAt()))
                .updatedAt(String.valueOf(entity.getUpdatedAt()))
                .build();
    }

    public TechnologyEntity toEntity(Technology domain) {
        TechnologyEntity entity = new TechnologyEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setDescription(domain.getDescription());
        return entity;
    }

    @Override
    public CompletableFuture<Boolean> existsByName(String name) {
        return technologyRepository.existsByName(name)  // ⇦ Mono<Boolean>
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)))
                .toFuture();    // ⇦ sin bloquear
//
////        Boolean result = technologyRepository.existsByName(name)
////                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)))
////                .onErrorMap(original -> new ProcessorException("Error checking technology existence", TechnicalMessage.BAD_REQUEST))
////                .block(); // bloquea y lanza si hay error
////
////        return Optional.ofNullable(result);
//
////        return technologyRepository.existsByName(name)
////                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)))
////                .flatMap(exists -> {
////                    if (exists) return Mono.just(true);
////                    return Mono.just(false);
////                })
////                .switchIfEmpty(Mono.error(new ProcessorException("Error checking technology existence", TechnicalMessage.BAD_REQUEST)));
    }
//
//    @Override
//    public Optional<Technology> createTechnology(TechnologyCreateRequestDto requestDto) {
//        return technologyRepository.existsByName(requestDto.getName())
//                .flatMap(exists -> technologyRepository.save(mapper.toEntityFromDto(requestDto))
//                        .flatMap(savedTechnology -> Mono.just(mapper.toDomainFromEntity(savedTechnology)))
//                        .switchIfEmpty(Mono.error(new ProcessorException("Error saving technology", TechnicalMessage.BAD_REQUEST))));
//    }
//
//    @Override
//    public Flux<Technology> existsTechnologies(ExistsTechnologiesRequestDto dto) {
//        return technologyRepository.findAllById(dto.getTechnologiesIds())
//                .flatMap(technologyEntity -> Mono.just(mapper.toDomainFromEntity(technologyEntity)))
//                .switchIfEmpty(Mono.error(new ProcessorException("Error fetching technologies", TechnicalMessage.BAD_REQUEST)));
//    }
//
//    @Override
//    public Optional<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto) {
//        List<TechnologyCapabilityEntity> entities = dto.getTechnologiesIds().stream()
//                .map(techId -> TechnologyCapabilityEntity.builder()
//                        .technologyId(techId)
//                        .capabilityId(dto.getCapabilityId())
//                        .build())
//                .toList();
//        return mapper.toDomainsFromEntities(technologyCapabilityRepository.saveAll(entities).collectList())
//                .switchIfEmpty(Mono.error(new ProcessorException("Error saving technologies-capabilities", TechnicalMessage.BAD_REQUEST)));
//    }
//
//    @Override
//    public Optional<List<TechnologyCapability>> findAllByCapabilityId(Long capabilityId) {
//        return mapper.toMonoTechnologyCapabilityListFromFluxEntities(technologyCapabilityRepository.findAllByCapabilityId(capabilityId))
//                .switchIfEmpty(Mono.error(new ProcessorException("Error fetching technologies-capabilities", TechnicalMessage.BAD_REQUEST)));
//
//    }
//
//    @Override
//    public Flux<Technology> findTechnologiesByIds(List<Long> technologiesIds) {
//        return technologyRepository.findAllById(technologiesIds)
//                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)))
//                .flatMap(technologyEntity -> Mono.just(mapper.toDomainFromEntity(technologyEntity)))
//                .switchIfEmpty(Mono.error(new ProcessorException("Error fetching technologies", TechnicalMessage.BAD_REQUEST)));
//    }
//
//    @Override
//    public Optional<Boolean> existsByCapabilityId(Long capabilityId) {
//        return technologyCapabilityRepository.existsByCapabilityId(capabilityId)
//                .flatMap(exists -> {
//                    if (exists) return Mono.just(true);
//                    return Mono.just(false);
//                })
//                .switchIfEmpty(Mono.error(new ProcessorException("Error checking technologies-capabilities existence", TechnicalMessage.BAD_REQUEST)));
//    }
}
