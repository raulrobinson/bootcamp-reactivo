package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.dto.request.CapabilityCreateDto;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import com.bootcamp.ws.domain.model.Capability;
import org.springframework.stereotype.Component;

@Component
public class CapabilityEntityMapper {

    public CapabilityEntity toCapabilityEntityFromDomain(Capability domain) {
        return CapabilityEntity.builder()
                .id(domain.getId())
                .name(domain.getName().toLowerCase())
                .description(domain.getDescription())
                .build();
    }

    public Capability toCapabilityDomainFromEntity(CapabilityEntity entity) {
        return Capability.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(String.valueOf(entity.getCreatedAt()))
                .updatedAt(String.valueOf(entity.getUpdatedAt()))
                .build();
    }

    public Capability toCapabilityEntityFromDto(CapabilityCreateDto dto) {
        return Capability.builder()
                .name(dto.getName().toLowerCase())
                .description(dto.getDescription())
                .technologyIds(dto.getTechnologyIds())
                .build();
    }
}
