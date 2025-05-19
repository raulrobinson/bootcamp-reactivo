package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.dto.TechnologyCreateDto;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;

@Component
public class TechnologyEntityMapper {

    public TechnologyEntity toTechnologyEntityFromDto(TechnologyCreateDto dto) {
        return TechnologyEntity.builder()
                .name(dto.getName().toLowerCase())
                .description(dto.getDescription())
                .build();
    }

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
