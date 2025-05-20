package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.dto.response.TechnologyCreateResponseDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TechnologyMapper {

    public Technology toTechnologyEntityFromDto(TechnologyCreateRequestDto dto) {
        return Technology.builder()
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

    public List<TechnologyCapability> toAssociateTechnologiesResponseDto(List<TechnologyCapabilityEntity> technologyCapabilityEntities) {
        return technologyCapabilityEntities.stream()
                .map(technologyCapabilityEntity -> TechnologyCapability.builder()
                        .capabilityId(technologyCapabilityEntity.getCapabilityId())
                        .technologyId(technologyCapabilityEntity.getTechnologyId())
                        .build())
                .toList();
    }
}
