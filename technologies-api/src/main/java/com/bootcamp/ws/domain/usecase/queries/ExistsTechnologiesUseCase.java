package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.spi.ExistsTechnologiesServicePort;
import com.bootcamp.ws.infrastructure.inbound.mapper.TechnologyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class ExistsTechnologiesUseCase implements ExistsTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;
    private final TechnologyMapper mapper;

    @Override
    public Flux<TechnologyResponseDto> existsTechnologies(ExistsTechnologiesDto dto) {
        return technologyAdapterPort.existsTechnologies(dto)
                .flatMap(technology -> Flux.just(mapper.toResponseTechnologyDto(technology)))
                .switchIfEmpty(Flux.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
    }
}
