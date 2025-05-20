package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.spi.ExistsTechnologiesServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExistsTechnologiesUseCase implements ExistsTechnologiesServicePort {

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;
    private final CapabilityEntityMapper mapper;

}
