package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import reactor.core.publisher.Mono;

import java.util.List;

public interface FindTechnologiesByIdsServicePort {
    Mono<List<TechnologyResponseDto>> findTechnologiesByIds(List<Long> technologiesIds);
}
