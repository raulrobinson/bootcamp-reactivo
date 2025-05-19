package com.bootcamp.ws.mapper;

import com.bootcamp.ws.dto.CapabilityCreateDto;
import com.bootcamp.ws.entity.CapabilityEntity;
import com.bootcamp.ws.model.Capability;
import org.springframework.stereotype.Component;

@Component
public class CapabilityMapper {

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
