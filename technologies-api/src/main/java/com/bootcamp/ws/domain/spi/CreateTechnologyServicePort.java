package com.bootcamp.ws.domain.spi;

import com.bootcamp.ws.domain.model.Technology;

import java.util.concurrent.CompletableFuture;

public interface CreateTechnologyServicePort {
    CompletableFuture<Technology> createTechnology(Technology requestDto);
}
