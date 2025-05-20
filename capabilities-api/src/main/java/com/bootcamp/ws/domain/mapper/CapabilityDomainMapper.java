package com.bootcamp.ws.domain.mapper;

import com.bootcamp.ws.domain.dto.request.CapabilityCreateDto;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.adapters.outbound.dto.TechnologyDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CapabilityDomainMapper {

    public Capability toDomainFromDto(CapabilityCreateDto capability) {
        if (capability == null) return null;

        List<Long> technologyIds = capability.getTechnologyIds().stream().toList();
//                .stream()
//                .map(TechnologyCapability::getTechnologyId)
//                .distinct()
//                .collect(Collectors.toList());

        List<TechnologyDto> technologyDtos = technologyIds.stream()
                .map(id -> TechnologyDto.builder()
                        .id(id)
                        .name(capability.getName())
                        .build())
                .collect(Collectors.toList());

        return Capability.builder()
                .name(capability.getName())
                .description(capability.getDescription())
                .technologies(technologyDtos)
                .build();
    }
}
