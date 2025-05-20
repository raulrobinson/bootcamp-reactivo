package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.dto.request.CapabilityCreateDto;
import com.bootcamp.ws.domain.dto.response.CapabilityResponseDto;
import reactor.core.publisher.Mono;

public interface CreateCapabilityServicePort {
    Mono<CapabilityResponseDto> createCapability(CapabilityCreateDto request);
}
