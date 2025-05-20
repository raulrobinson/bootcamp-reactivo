package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import reactor.core.publisher.Flux;

public interface ExistsTechnologiesServicePort {
    Flux<TechnologyResponseDto> existsTechnologies(ExistsTechnologiesRequestDto dto);
}
