package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.infrastructure.adapters.outbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.infrastructure.adapters.outbound.dto.TechnologyDto;
import com.bootcamp.ws.infrastructure.adapters.outbound.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.infrastructure.adapters.outbound.model.TechnologyCapability;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyExternalAdapterPort {
    Mono<List<TechnologyDto>> existsTechnologies(ExistsTechnologiesDto dto);

    Mono<List<TechnologyCapability>> associateTechnologies(TechnologyAssociateTechnologies request);
}
