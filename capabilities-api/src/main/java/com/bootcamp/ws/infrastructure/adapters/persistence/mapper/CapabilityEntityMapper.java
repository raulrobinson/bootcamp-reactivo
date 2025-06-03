package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.CapabilityEntity;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.inbound.dto.CapabilityCreateDto;
import io.r2dbc.spi.Readable;
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

    public CapabilityEntity capabilityRowMapper(Readable row) {
        return CapabilityEntity.builder()
                .id(row.get("id", Long.class))
                .name(row.get("name", String.class))
                .description(row.get("description", String.class))
                .build();
    }

    public Capability toCapabilityFullListFromEntity(CapabilityEntity entity) {
        if (entity == null) return null;
        return Capability.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }
}
