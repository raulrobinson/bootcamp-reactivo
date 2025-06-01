package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

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
        return Technology.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
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

    public Iterable<TechnologyCapabilityEntity> toTechnologyCapabilityEntitiesFromDomains(List<TechnologyCapability> entities) {
        if (entities == null || entities.isEmpty()) return List.of();
        return entities.stream()
                .map(entity -> TechnologyCapabilityEntity.builder()
                        .technologyId(entity.getTechnologyId())
                        .capabilityId(entity.getCapabilityId())
                        .build())
                .toList();
    }
}
