package com.bootcamp.ws.domain.mapper;

import com.bootcamp.ws.domain.dto.response.TechnologyCreateResponseDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TechnologyDomainMapper {

    public TechnologyCreateResponseDto toResponseTechnologyCreateDto(Technology technology) {
        if (technology == null) return null;
        return TechnologyCreateResponseDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .description(technology.getDescription())
                .build();
    }

    public TechnologyResponseDto toResponseTechnologyDto(Technology technology) {
        if (technology == null) return null;
        return TechnologyResponseDto.builder()
                .id(technology.getId())
                .name(technology.getName())
                .build();
    }

    public List<TechnologyResponseDto> toDtoListFromDomainList(List<Technology> technologies) {
        if (technologies == null) return null;
        return technologies.stream()
                .map(this::toResponseTechnologyDto)
                .toList();
    }
}
