package com.bootcamp.ws.domain.spi;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public interface FindAssociatesTechsByCapIdServicePort {
    CompletableFuture<Map<Object, Object>> findAssociatesTechsByCapId(Long capabilityId);
}
