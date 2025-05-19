package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import reactor.core.publisher.Flux;

public interface ExistsTechnologiesServicePort {
    Flux<TechnologyResponseDto> existsTechnologies(ExistsTechnologiesDto dto);
}
