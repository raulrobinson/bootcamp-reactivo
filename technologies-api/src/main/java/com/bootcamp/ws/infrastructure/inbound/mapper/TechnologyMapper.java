package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;

@Component
public class TechnologyMapper {

    public TechnologyResponseDto toResponseTechnologyDto(Technology technology) {
        if (technology == null) return null;
        return TechnologyResponseDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .build();
    }
}
