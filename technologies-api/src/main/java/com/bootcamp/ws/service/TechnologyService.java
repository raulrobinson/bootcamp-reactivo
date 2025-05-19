package com.bootcamp.ws.service;

import com.bootcamp.ws.dto.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.entity.TechnologyEntity;
import com.bootcamp.ws.entity.TechnologyCapabilityEntity;
import com.bootcamp.ws.model.Technology;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyService {
    Flux<Technology> existsTechnologies(ExistsTechnologiesDto dto);

    Mono<Technology> createTechnology(TechnologyEntity technologyEntity);

    Mono<List<TechnologyCapabilityEntity>> associateTechnologies(AssociateTechnologiesCreateDto dto);
}
