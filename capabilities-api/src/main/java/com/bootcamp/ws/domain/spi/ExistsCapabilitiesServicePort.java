package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Capability;
import reactor.core.publisher.Flux;

import java.util.List;

public interface ExistsCapabilitiesServicePort {
    Flux<Capability> existsCapabilities(List<Long> longs);
}
