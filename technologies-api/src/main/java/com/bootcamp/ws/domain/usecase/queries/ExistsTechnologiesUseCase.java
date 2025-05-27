package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.spi.ExistsTechnologiesServicePort;

public class ExistsTechnologiesUseCase implements ExistsTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public ExistsTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

//    @Override
//    public Flux<TechnologyResponseDto> existsTechnologies(ExistsTechnologiesRequestDto dto) {
//        return technologyAdapterPort.existsTechnologies(dto)
//                .flatMap(technology -> Flux.just(mapper.toResponseTechnologyDto(technology)))
//                .switchIfEmpty(Flux.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
//    }
}
