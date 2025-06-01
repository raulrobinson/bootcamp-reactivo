package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologiesByIdsRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class TechnologyMapper {

    public Technology toDomainFromDto(TechnologyCreateRequestDto dto) {
        return Optional.ofNullable(dto)
                .map(d -> Technology.builder()
                        .name(d.getName())
                        .description(d.getDescription())
                        .build())
                .orElse(null);
    }

    public TechnologyResponseDto toResponseDto(Technology tech) {
        if (tech == null) return null;

        return TechnologyResponseDto.builder()
                .id(tech.getId())
                .name(tech.getName())
                .description(tech.getDescription())
                .build();
    }

    public Mono<TechnologyResponseDto> toResponseTechnologyDto(Mono<Technology> futureTech) {
        return futureTech.map(this::toResponseDto);
    }

    public List<Long> toDomainFromTechnologiesByIdsRequestDto(TechnologiesByIdsRequestDto request) {
        return Optional.ofNullable(request)
                .map(TechnologiesByIdsRequestDto::getTechnologiesIds)
                .orElse(List.of());
    }
}
