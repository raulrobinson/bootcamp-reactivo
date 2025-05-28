package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.model.Technology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateTechnologyUseCaseTest {

    private TechnologyAdapterPort technologyAdapterPort;
    private CreateTechnologyUseCase createTechnologyUseCase;

    Technology request = new Technology.Builder().name("Java").build();

    @BeforeEach
    public void setUp() {
        technologyAdapterPort = mock(TechnologyAdapterPort.class);
        createTechnologyUseCase = new CreateTechnologyUseCase(technologyAdapterPort);
    }

    @Test
    public void shouldCreateTechnologyWhenNotExists() throws Exception {
        when(technologyAdapterPort.existsByName("Java"))
                .thenReturn(CompletableFuture.completedFuture(false));
        when(technologyAdapterPort.createTechnology(request))
                .thenReturn(CompletableFuture.completedFuture(request));

        Technology result = createTechnologyUseCase.createTechnology(request).get();

        assertNotNull(result);
        assertEquals("Java", result.getName());
        verify(technologyAdapterPort).existsByName("Java");
        verify(technologyAdapterPort).createTechnology(request);
    }

    @Test
    public void shouldThrowDuplicateResourceExceptionWhenTechnologyExists() {
        when(technologyAdapterPort.existsByName("Java"))
                .thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<Technology> future = createTechnologyUseCase.createTechnology(request);

        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertInstanceOf(DuplicateResourceException.class, exception.getCause());
        assertEquals("Already exists", exception.getCause().getMessage());

        verify(technologyAdapterPort).existsByName("Java");
        verify(technologyAdapterPort, never()).createTechnology(any());
    }
}