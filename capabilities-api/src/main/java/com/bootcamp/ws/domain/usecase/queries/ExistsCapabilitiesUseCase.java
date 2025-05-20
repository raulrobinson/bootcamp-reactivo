package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.spi.ExistsCapabilitiesServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExistsCapabilitiesUseCase implements ExistsCapabilitiesServicePort {

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;
    private final CapabilityEntityMapper mapper;

    @Override
    public Flux<Capability> existsCapabilities(List<Long> req) {
        return capabilityPersistenceAdapterPort.findAllById(req)
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
    }
}
