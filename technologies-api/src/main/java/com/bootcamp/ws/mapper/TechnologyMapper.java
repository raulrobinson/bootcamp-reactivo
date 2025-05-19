package com.bootcamp.ws.mapper;

import com.bootcamp.ws.dto.TechnologyCreateDto;
import com.bootcamp.ws.entity.TechnologyEntity;
import com.bootcamp.ws.model.Technology;
import org.springframework.stereotype.Component;

@Component
public class TechnologyMapper {

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
