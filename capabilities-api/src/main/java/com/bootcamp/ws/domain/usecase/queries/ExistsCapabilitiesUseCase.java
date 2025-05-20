package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.CapabilityPersistenceAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.mapper.CapabilityDomainMapper;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.domain.spi.ExistsCapabilitiesServicePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class ExistsCapabilitiesUseCase implements ExistsCapabilitiesServicePort {

    private final CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort;
    private final CapabilityDomainMapper mapper;

    public ExistsCapabilitiesUseCase(CapabilityPersistenceAdapterPort capabilityPersistenceAdapterPort, CapabilityDomainMapper mapper) {
        this.capabilityPersistenceAdapterPort = capabilityPersistenceAdapterPort;
        this.mapper = mapper;
    }

    @Override
    public Flux<Capability> existsCapabilities(List<Long> req) {
//        capabilityPersistenceAdapterPort.findAssociatesTechsByCapId(1L);

        return capabilityPersistenceAdapterPort.findAllCapabilitiesByIds(req)
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
    }
}
