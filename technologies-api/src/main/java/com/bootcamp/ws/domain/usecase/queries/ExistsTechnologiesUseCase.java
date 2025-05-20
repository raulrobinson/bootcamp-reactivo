package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.mapper.TechnologyDomainMapper;
import com.bootcamp.ws.domain.spi.ExistsTechnologiesServicePort;
import reactor.core.publisher.Flux;

public class ExistsTechnologiesUseCase implements ExistsTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;
    private final TechnologyDomainMapper mapper;

    public ExistsTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort, TechnologyDomainMapper mapper) {
        this.technologyAdapterPort = technologyAdapterPort;
        this.mapper = mapper;
    }

    @Override
    public Flux<TechnologyResponseDto> existsTechnologies(ExistsTechnologiesRequestDto dto) {
        return technologyAdapterPort.existsTechnologies(dto)
                .flatMap(technology -> Flux.just(mapper.toResponseTechnologyDto(technology)))
                .switchIfEmpty(Flux.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
    }
}
