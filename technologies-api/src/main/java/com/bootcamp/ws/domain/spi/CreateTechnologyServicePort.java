package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.domain.dto.response.TechnologyCreateResponseDto;
import reactor.core.publisher.Mono;

public interface CreateTechnologyServicePort {
    Mono<TechnologyCreateResponseDto> createTechnology(TechnologyCreateRequestDto requestDto);
}
