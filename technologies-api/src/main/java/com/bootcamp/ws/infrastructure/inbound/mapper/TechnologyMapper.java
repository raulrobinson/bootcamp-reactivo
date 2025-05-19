package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.dto.request.TechnologyCreateDto;
import com.bootcamp.ws.domain.dto.response.TechnologyCreateResponseDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import org.springframework.stereotype.Component;

@Component
public class TechnologyMapper {

    public TechnologyEntity toTechnologyEntityFromDto(TechnologyCreateDto dto) {
        return TechnologyEntity.builder()
                .name(dto.getName().toLowerCase())
                .description(dto.getDescription())
                .build();
    }

    public TechnologyResponseDto toResponseTechnologyDto(Technology technology) {
        return TechnologyResponseDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .build();
    }

    public TechnologyCreateResponseDto toResponseTechnologyCreateDto(Technology technology) {
        return TechnologyCreateResponseDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .description(technology.getDescription())
                .build();
    }
}
