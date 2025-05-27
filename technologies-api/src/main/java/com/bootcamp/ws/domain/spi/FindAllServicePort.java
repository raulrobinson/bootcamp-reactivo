package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Technology;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface FindAllServicePort {
    CompletableFuture<List<Technology>> findAll();
}
