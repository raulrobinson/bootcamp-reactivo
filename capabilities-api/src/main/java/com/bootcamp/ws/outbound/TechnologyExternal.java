package com.bootcamp.ws.outbound;

import com.bootcamp.ws.outbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.outbound.dto.TechnologyDto;
import com.bootcamp.ws.outbound.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.outbound.model.TechnologyCapability;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyExternal {
    Mono<List<TechnologyDto>> existsTechnologies(ExistsTechnologiesDto dto);

    Mono<List<TechnologyCapability>> associateTechnologies(TechnologyAssociateTechnologies request);
}
