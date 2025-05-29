package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Capability;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ExistsCapabilitiesServicePort {
    CompletableFuture<Capability> existsCapabilities(List<Long> longs);
}
