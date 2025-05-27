package com.bootcamp.ws.domain.spi;

import java.util.concurrent.CompletableFuture;

public interface ExistsByNameServicePort {
    CompletableFuture<Boolean> existsByName(String name);
}
