package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.spi.FindTechnologiesByIdsServicePort;

public class FindTechnologiesByIdsUseCase implements FindTechnologiesByIdsServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public FindTechnologiesByIdsUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

//    @Override
//    public Mono<List<TechnologyResponseDto>> findTechnologiesByIds(List<Long> technologiesIds) {
//        return technologyAdapterPort.findTechnologiesByIds(technologiesIds)
//                .collectList()
//                .map(mapper::toDtoListFromDomainList);
//    }
}
