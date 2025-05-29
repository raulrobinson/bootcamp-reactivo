package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import org.springframework.stereotype.Component;

@Component
public class CapabilityEntityMapper {
    public CapabilityEntity toEntityFromDomain(Capability domain) {
        if (domain == null) return null;
        return CapabilityEntity.builder()
                .id(domain.getId())
                .name(domain.getName().toLowerCase())
                .description(domain.getDescription())
                .build();
    }

    public Capability toDomainFromEntity(CapabilityEntity entity) {
        if (entity == null) return null;
        return Capability.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(String.valueOf(entity.getCreatedAt()))
                .updatedAt(String.valueOf(entity.getUpdatedAt()))
                .build();
    }
}
