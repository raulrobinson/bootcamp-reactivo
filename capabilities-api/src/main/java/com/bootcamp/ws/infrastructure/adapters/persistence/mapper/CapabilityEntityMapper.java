package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.inbound.dto.request.CapabilityCreateDto;
import org.springframework.stereotype.Component;

@Component
public class CapabilityEntityMapper {

    public CapabilityEntity toCapabilityEntityFromDomain(Capability domain) {
        if (domain == null) return null;
        return CapabilityEntity.builder()
                .id(domain.getId())
                .name(domain.getName().toLowerCase())
                .description(domain.getDescription())
                .build();
    }

    public Capability toCapabilityDomainFromEntity(CapabilityEntity entity) {
        if (entity == null) return null;
        return Capability.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public Capability toDomain(CapabilityCreateDto dto) {
        if (dto == null) return null;
        return Capability.builder()
                .name(dto.getName().toLowerCase())
                .description(dto.getDescription())
                .technologyIds(dto.getTechnologyIds())
                .build();
    }
}
