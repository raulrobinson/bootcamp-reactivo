package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.mapper.TechnologyDomainMapper;
import com.bootcamp.ws.domain.spi.FindTechnologiesByIdsServicePort;
import reactor.core.publisher.Mono;

import java.util.List;

public class FindTechnologiesByIdsUseCase implements FindTechnologiesByIdsServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;
    private final TechnologyDomainMapper mapper;

    public FindTechnologiesByIdsUseCase(TechnologyAdapterPort technologyAdapterPort, TechnologyDomainMapper mapper) {
        this.technologyAdapterPort = technologyAdapterPort;
        this.mapper = mapper;
    }

    @Override
    public Mono<List<TechnologyResponseDto>> findTechnologiesByIds(List<Long> technologiesIds) {
        return technologyAdapterPort.findTechnologiesByIds(technologiesIds)
                .collectList()
                .map(mapper::toDtoListFromDomainList);
//                .map(technologies -> technologies.stream()
//                        .map(technology -> TechnologyResponseDto.builder()
//                                .id(technology.getId())
//                                .name(technology.getName())
//                                .build())
//                        .toList());
    }
}
