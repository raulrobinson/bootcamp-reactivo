package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.infrastructure.inbound.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologiesByIdsRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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

    public CompletableFuture<TechnologyResponseDto> toResponseTechnologyDto(CompletableFuture<Technology> futureTech) {
        return futureTech.thenApply(this::toResponseDto);
    }

    public List<Long> toDomainFromTechnologiesByIdsRequestDto(TechnologiesByIdsRequestDto request) {
        return Optional.ofNullable(request)
                .map(TechnologiesByIdsRequestDto::getTechnologiesIds)
                .orElse(List.of());
    }

    public Tuple2<Long, List<Long>> toDomainFromCapabilityIdAndTechnologiesByIdsRequestDto(
            AssociateTechnologiesCreateRequestDto request) {

        Long capabilityId = request.getCapabilityId();
        List<Long> technologiesIds = Optional.ofNullable(request.getTechnologiesIds()).orElse(List.of());

        return Tuples.of(capabilityId, technologiesIds);
    }
}
