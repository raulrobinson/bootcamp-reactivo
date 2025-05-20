package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.NotFoundException;
import com.bootcamp.ws.domain.dto.response.CapabilityWithTechnologiesResponseDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.spi.FindAssociatesTechsByCapIdServicePort;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

public class FindAssociatesTechsByCapIdUseCase implements FindAssociatesTechsByCapIdServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public FindAssociatesTechsByCapIdUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public Mono<CapabilityWithTechnologiesResponseDto> findAssociatesTechsByCapId(Long capabilityId) {
        return technologyAdapterPort.findAllByCapabilityId(capabilityId)
                .flatMap(list -> groupTechnologiesByCapability(list, capabilityId));
    }

    public Mono<CapabilityWithTechnologiesResponseDto> groupTechnologiesByCapability(List<TechnologyCapability> input,
                                                                                     Long capabilityId) {
        if (input == null || input.isEmpty()) return Mono.error(new NotFoundException(TechnicalMessage.NOT_FOUND, capabilityId.toString()));
        Long capId = input.getFirst().getCapabilityId();

        List<Long> technologyIds = input.stream()
                .map(TechnologyCapability::getTechnologyId)
                .distinct()
                .collect(Collectors.toList());

        return technologyAdapterPort.findTechnologiesByIds(technologyIds)
                .collectList()
                .map(technologies -> CapabilityWithTechnologiesResponseDto.builder()
                        .capabilityId(capId)
                        .technologiesIds(technologies.stream()
                                .map(tech -> TechnologyResponseDto.builder()
                                        .id(tech.getId())
                                        .name(tech.getName())
                                        .build())
                                .collect(Collectors.toList()))
                        .build());
    }

}
