package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.model.Technology;
import reactor.core.publisher.Flux;

public interface ExistsTechnologiesServicePort {
    Flux<Technology> existsTechnologies(ExistsTechnologiesDto dto);
}
