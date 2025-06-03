package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.model.Bootcamp;
import com.bootcamp.ws.infrastructure.inbound.dto.BootcampDTO;
import org.springframework.stereotype.Component;

@Component
public class BootcampMapper {

    public Bootcamp toDomain(BootcampDTO dto) {
        if (dto == null) return null;
        return Bootcamp.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();
    }
}
