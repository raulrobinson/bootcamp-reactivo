package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.model.Bootcamp;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.BootcampEntity;
import org.springframework.stereotype.Component;

@Component
public class BootcampEntityMapper {

    public Bootcamp toDomain(BootcampEntity bootcampEntity) {
        if (bootcampEntity == null) return null;
        return Bootcamp.builder()
                .id(bootcampEntity.getId())
                .name(bootcampEntity.getName())
                .description(bootcampEntity.getDescription())
                .build();
    }

    public BootcampEntity toEntity(Bootcamp bootcamp) {
        if (bootcamp == null) return null;
        return BootcampEntity.builder()
                .id(bootcamp.getId())
                .name(bootcamp.getName())
                .description(bootcamp.getDescription())
                .build();
    }
}
