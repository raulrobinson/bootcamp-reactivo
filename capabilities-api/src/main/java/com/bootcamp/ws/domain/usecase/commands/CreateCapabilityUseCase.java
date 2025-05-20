package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.BusinessException;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.spi.CreateCapabilityServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCapabilityUseCase implements CreateCapabilityServicePort {

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;
    private final CapabilityEntityMapper mapper;

    @Override
    public Mono<Capability> createCapability(Capability request) {
        return capabilityPersistenceAdapterPort.createCapability(request)
                .switchIfEmpty(Mono.error(new BusinessException(TechnicalMessage.BAD_REQUEST)));
    }
}
