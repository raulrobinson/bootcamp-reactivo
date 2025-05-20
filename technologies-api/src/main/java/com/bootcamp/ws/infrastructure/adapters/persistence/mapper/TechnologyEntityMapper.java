package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
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
        return Technology.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(String.valueOf(entity.getCreatedAt()))
                .updatedAt(String.valueOf(entity.getUpdatedAt()))
                .build();
    }

    public Mono<List<TechnologyCapability>> toMonoTechnologyCapabilityListFromFluxEntities(Flux<TechnologyCapabilityEntity> entities) {
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
}
