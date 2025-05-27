package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
public class TechnologyMapper {

    public Technology toDomainFromDto(TechnologyCreateRequestDto dto) {
        return Optional.ofNullable(dto)
                .map(d -> new Technology.Builder()
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

    public CompletableFuture<TechnologyResponseDto> toResponseTechnologyDto(CompletableFuture<Technology> futureTech) {
        return futureTech.thenApply(this::toResponseDto);
    }
}
