package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.infrastructure.adapters.outbound.Response;
import com.bootcamp.ws.infrastructure.inbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyExternalAdapterPort {
    Mono<List<Technology>> findTechnologiesByIdIn(ExistsTechnologiesDto dto);
    Mono<List<TechnologyCapability>> associateTechnologies(TechnologyAssociateTechnologies request);
    Mono<Response> findAssociatesTechsByCapId(Long capabilityId);

    Mono<Boolean> deleteAssocTechnologiesByCapabilityId(Long capabilityId);
}
