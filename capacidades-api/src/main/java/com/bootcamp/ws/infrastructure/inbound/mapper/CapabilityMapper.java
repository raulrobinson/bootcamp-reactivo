package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.inbound.dto.request.CapabilityCreateDto;
import com.bootcamp.ws.infrastructure.inbound.dto.response.CapabilityResponseDto;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class CapabilityMapper {

    public Capability toDomainFromDto(CapabilityCreateDto dto) {
        if (dto == null) return null;

        Capability capability = new Capability();
        capability.setName(dto.getName());
        capability.setDescription(dto.getDescription());
        capability.setTechnologies(dto.getTechnologyIds());

        return capability;
    }

    public CapabilityResponseDto toResponseDto(Capability cap) {
        if (cap == null) return null;

        return CapabilityResponseDto.builder()
                .id(cap.getId())
                .name(cap.getName())
                .technologies(cap.getTechnologies())
                .build();
    }

    public CompletableFuture<CapabilityResponseDto> toResponseCapabilityDto(CompletableFuture<Capability> capability) {
        return capability.thenApply(this::toResponseDto);
    }
}
