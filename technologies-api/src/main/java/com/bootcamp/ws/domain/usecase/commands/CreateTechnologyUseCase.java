package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.dto.response.TechnologyCreateResponseDto;
import com.bootcamp.ws.domain.spi.CreateTechnologyServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.bootcamp.ws.infrastructure.inbound.mapper.TechnologyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CreateTechnologyUseCase implements CreateTechnologyServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;
    private final TechnologyMapper mapper;

    @Override
    public Mono<TechnologyCreateResponseDto> createTechnology(TechnologyEntity technologyEntity) {
        return technologyAdapterPort.createTechnology(technologyEntity)
                .map(mapper::toResponseTechnologyCreateDto);
    }
}
