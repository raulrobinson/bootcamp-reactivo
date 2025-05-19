package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;

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

}
