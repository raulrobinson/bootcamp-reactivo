package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.BusinessException;
import com.bootcamp.ws.domain.common.exceptions.DuplicateException;
import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.dto.response.TechnologyCreateResponseDto;
import com.bootcamp.ws.domain.mapper.TechnologyDomainMapper;
import com.bootcamp.ws.domain.spi.CreateTechnologyServicePort;

import reactor.core.publisher.Mono;

public class CreateTechnologyUseCase implements CreateTechnologyServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;
    private final TechnologyDomainMapper mapper;

    public CreateTechnologyUseCase(TechnologyAdapterPort technologyAdapterPort, TechnologyDomainMapper mapper) {
        this.technologyAdapterPort = technologyAdapterPort;
        this.mapper = mapper;
    }

    @Override
    public Mono<TechnologyCreateResponseDto> createTechnology(TechnologyCreateRequestDto requestDto) {
        return technologyAdapterPort.existsByName(requestDto.getName())
                .flatMap(exists -> {
                    if (exists) return Mono.error(new DuplicateException(TechnicalMessage.ALREADY_EXISTS));
                    return technologyAdapterPort.createTechnology(requestDto)
                            .map(mapper::toResponseTechnologyCreateDto);
                })
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST)));
    }
}
