package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.response.TechnologyCreateResponseDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import reactor.core.publisher.Mono;

public interface CreateTechnologyServicePort {
    Mono<TechnologyCreateResponseDto> createTechnology(TechnologyEntity technologyEntity);
}
