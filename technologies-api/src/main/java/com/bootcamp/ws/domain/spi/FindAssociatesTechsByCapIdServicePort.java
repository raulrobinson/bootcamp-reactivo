package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.response.CapabilityWithTechnologiesDto;
import reactor.core.publisher.Mono;

public interface FindAssociatesTechsByCapIdServicePort {
    Mono<CapabilityWithTechnologiesDto> findAssociatesTechsByCapId(Long capabilityId);
}
