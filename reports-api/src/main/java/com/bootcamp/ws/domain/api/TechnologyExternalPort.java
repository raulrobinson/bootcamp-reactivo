package com.bootcamp.ws.domain.api;

import com.bootcamp.ws.domain.dto.ExistsTechnologies;
import com.bootcamp.ws.domain.dto.Response;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import reactor.core.publisher.Mono;

import java.util.List;

public interface TechnologyExternalPort {
    Mono<List<Technology>> findTechnologiesByIdIn(ExistsTechnologies dto);

    Mono<List<TechnologyCapability>> associateTechnologies(TechnologyAssociateTechnologies request);

    Mono<Response> findAssociatesTechsByCapId(Long capabilityId);

    Mono<Boolean> deleteAssocTechnologiesByCapabilityId(Long capabilityId);
}
