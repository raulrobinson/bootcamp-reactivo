package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TechnologyEntityMapper {

    public Technology toDomainFromEntity(TechnologyEntity entity) {
        if (entity == null) return null;

        return new Technology.Builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(String.valueOf(entity.getCreatedAt()))
                .updatedAt(String.valueOf(entity.getUpdatedAt()))
//                .createdAt(entity.getCreatedAt() != null ? entity.getCreatedAt().toString() : null)
//                .updatedAt(entity.getUpdatedAt() != null ? entity.getUpdatedAt().toString() : null)
                .build();
    }


    public TechnologyEntity toEntityFromDomain(Technology technology) {
        if (technology == null) return null;
        return TechnologyEntity.builder()
                .id(technology.getId())
                .name(technology.getName().toLowerCase())
                .description(technology.getDescription())
                .build();
    }

    public Mono<List<TechnologyCapability>> toMonoTechnologyCapabilityListFromFluxEntities(Flux<TechnologyCapabilityEntity> entities) {
        if (entities == null) return null;
        return entities.collectList()
                .map(entityList -> entityList.stream()
                        .map(entity -> TechnologyCapability.builder()
                                .technologyId(entity.getTechnologyId())
                                .capabilityId(entity.getCapabilityId())
                                .build())
                        .toList());
    }

    public TechnologyEntity toEntityFromDto(TechnologyCreateRequestDto requestDto) {
        if (requestDto == null) return null;
        return TechnologyEntity.builder()
                .name(requestDto.getName().toLowerCase())
                .description(requestDto.getDescription())
                .build();
    }

    public Mono<List<TechnologyCapability>> toDomainsFromEntities(Mono<List<TechnologyCapabilityEntity>> listMono) {
        if (listMono == null) return null;
        return listMono.flatMap(entities -> {
            if (entities == null) return Mono.empty();
            return toMonoTechnologyCapabilityListFromFluxEntities(Flux.fromIterable(entities));
        });
    }
}
