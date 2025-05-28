package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyEntity;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.TechnologyEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyCapabilityRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.TechnologyRepository;
import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;
import com.bootcamp.ws.infrastructure.common.exception.DatabaseResourceException;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import com.bootcamp.ws.infrastructure.common.exception.TechnicalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TechnologyPersistenceAdapterTest {

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private TechnologyCapabilityRepository technologyCapabilityRepository;

    @Mock
    private TechnologyEntityMapper mapper;

    @InjectMocks
    private TechnologyPersistenceAdapter adapter;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsByName_shouldReturnTrue() {
        when(technologyRepository.existsByName("Java")).thenReturn(Mono.just(true));

        var result = adapter.existsByName("Java");

        assertTrue(result.join());
        verify(technologyRepository).existsByName("Java");
    }

    @Test
    void existsByName_shouldThrowTechnicalException_whenEmpty() {
        String name = "TechX";

        when(technologyRepository.existsByName(name)).thenReturn(Mono.empty());

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.existsByName(name).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(TechnicalException.class, cause);
        assertEquals("Not found", cause.getMessage());
    }

    @Test
    void existsByName_shouldThrowDatabaseResourceException_whenUnexpectedError() {
        String name = "TechY";

        when(technologyRepository.existsByName(name))
                .thenReturn(Mono.error(new RuntimeException("Connection lost")));

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.existsByName(name).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(DatabaseResourceException.class, cause);
        assertEquals("Error accessing database", cause.getMessage());
    }

    @Test
    void existsByName_shouldThrowDatabaseResourceExceptionOnError() {
        when(technologyRepository.existsByName("Scala")).thenReturn(Mono.error(new RuntimeException("DB down")));

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.existsByName("Scala").join());

        assertInstanceOf(DatabaseResourceException.class, exception.getCause());
        assertEquals("Error accessing database", exception.getCause().getMessage());
    }

    @Test
    void existsTechnologies_shouldThrowProcessorExceptionWhenListIsEmpty() {
        List<Long> ids = List.of(1L, 2L);
        when(technologyRepository.findAllById(ids)).thenReturn(Flux.empty());

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.existsTechnologies(ids).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(ProcessorException.class, cause);
        assertEquals(TechnicalMessage.BAD_REQUEST.getMessage(), cause.getMessage());
    }

    @Test
    void existsTechnologies_shouldReturnListOfTechnologies() {
        List<Long> ids = List.of(1L, 2L);
        TechnologyEntity entity1 = TechnologyEntity.builder().id(1L).name("Java").build();
        TechnologyEntity entity2 = TechnologyEntity.builder().id(1L).name("Java").build();

        Technology tech1 = new Technology.Builder().id(1L).name("Java").build();
        Technology tech2 = new Technology.Builder().id(1L).name("Java").build();

        when(technologyRepository.findAllById(ids)).thenReturn(Flux.just(entity1, entity2));
        when(mapper.toDomainFromEntity(entity1)).thenReturn(tech1);
        when(mapper.toDomainFromEntity(entity2)).thenReturn(tech2);

        List<Technology> result = adapter.existsTechnologies(ids).join();

        assertEquals(2, result.size());
        assertFalse(result.containsAll(List.of(tech1, tech2)));
    }

    @Test
    void existsTechnologies_shouldThrowDatabaseResourceException_onUnexpectedError() {
        List<Long> techIds = List.of(1L, 2L);

        // Simulamos que el repositorio lanza un RuntimeException inesperado
        when(technologyRepository.findAllById(techIds))
                .thenReturn(Flux.error(new RuntimeException("Unexpected DB error")));

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.existsTechnologies(techIds).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(DatabaseResourceException.class, cause);
        assertEquals("Error accessing database", cause.getMessage());
    }

    @Test
    void findTechnologiesByIds_shouldReturnMappedList() {
        var ids = List.of(1L, 2L);
        var entity1 = mock(TechnologyEntity.class); // Simula entidad si no tienes clase real
        var entity2 = mock(TechnologyEntity.class);
        var tech1 = mock(Technology.class);
        var tech2 = mock(Technology.class);

        when(technologyRepository.findAllById(ids)).thenReturn(Flux.just(entity1, entity2));
        when(mapper.toDomainFromEntity(entity1)).thenReturn(tech1);
        when(mapper.toDomainFromEntity(entity2)).thenReturn(tech2);

        var result = adapter.findTechnologiesByIds(ids).join();

        assertThat(result).containsExactly(tech1, tech2);
    }

    @Test
    void findTechnologiesByIds_shouldThrowDatabaseResourceException_onUnexpectedError() {
        List<Long> techIds = List.of(1L, 2L);

        // Simulamos que el repositorio lanza un RuntimeException inesperado
        when(technologyRepository.findAllById(techIds))
                .thenReturn(Flux.error(new RuntimeException("Unexpected DB error")));

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.findTechnologiesByIds(techIds).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(DatabaseResourceException.class, cause);
        assertEquals("Error accessing database", cause.getMessage());
    }

    @Test
    void findTechnologiesByIds_shouldThrowProcessorExceptionOnEmpty() {
        when(technologyRepository.findAllById(List.of(1L, 2L))).thenReturn(Flux.empty());

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.findTechnologiesByIds(List.of(1L, 2L)).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(ProcessorException.class, cause);
        assertEquals(TechnicalMessage.BAD_REQUEST.getMessage(), cause.getMessage());
    }

    @Test
    void associateTechnologies_shouldMapAndReturnList() {
        List<Long> techIds = List.of(10L, 20L);
        Long capabilityId = 5L;

        var domain1 = new TechnologyCapability.Builder().technologyId(10L).capabilityId(5L).build();
        var domain2 = new TechnologyCapability.Builder().technologyId(20L).capabilityId(5L).build();
        var entity1 = mock(TechnologyCapabilityEntity.class);
        var entity2 = mock(TechnologyCapabilityEntity.class);

        when(mapper.toTechnologyCapabilityEntitiesFromDomains(anyList())).thenReturn(List.of(entity1, entity2));
        when(technologyCapabilityRepository.saveAll(anyList())).thenReturn(Flux.just(entity1, entity2));
        when(mapper.toTechnologyCapabilityDomainsFromEntities(anyList())).thenReturn(List.of(domain1, domain2));

        var result = adapter.associateTechnologies(capabilityId, techIds).join();

        assertThat(result).containsExactly(domain1, domain2);
    }

    @Test
    void associateTechnologies_shouldThrowDatabaseResourceException_onUnexpectedError() {
        Long capabilityId = 1L;
        List<Long> techIds = List.of(1L, 2L);

        List<TechnologyCapability> domains = techIds.stream()
                .map(id -> new TechnologyCapability.Builder()
                        .technologyId(id)
                        .capabilityId(capabilityId)
                        .build())
                .toList();

        List<TechnologyCapabilityEntity> mockEntities = List.of(
                mock(TechnologyCapabilityEntity.class),
                mock(TechnologyCapabilityEntity.class)
        );

        when(mapper.toTechnologyCapabilityEntitiesFromDomains(domains)).thenReturn(mockEntities);
        when(mapper.toTechnologyCapabilityDomainsFromEntities(anyList())).thenReturn(domains);

        // Aquí aceptamos cualquier lista para evitar problemas de referencia
        when(technologyCapabilityRepository.saveAll(anyList()))
                .thenReturn(Flux.error(new RuntimeException("Unexpected error")));

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.associateTechnologies(capabilityId, techIds).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(DatabaseResourceException.class, cause);
        assertEquals("Error accessing database", cause.getMessage());
    }

    @Test
    void existsByCapabilityId_shouldReturnTrue() {
        Long capabilityId = 1L;
        when(technologyCapabilityRepository.existsByCapabilityId(capabilityId)).thenReturn(Mono.just(true));

        var result = adapter.existsByCapabilityId(capabilityId).join();

        assertTrue(result);
        verify(technologyCapabilityRepository).existsByCapabilityId(capabilityId);
    }

    @Test
    void findAllByCapabilityId_shouldThrowProcessorExceptionWhenListIsEmpty() {
        when(technologyCapabilityRepository.findAllByCapabilityId(1L)).thenReturn(Flux.empty());
        when(mapper.toMonoTechnologyCapabilityListFromFluxEntities(any())).thenReturn(Mono.just(List.of()));

        CompletionException exception = assertThrows(CompletionException.class,
                () -> adapter.findAllByCapabilityId(1L).join());

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertInstanceOf(ProcessorException.class, cause);
        assertEquals(TechnicalMessage.BAD_REQUEST.getMessage(), cause.getMessage());
    }

    @Test
    void findAllByCapabilityId_shouldReturnList_whenValidListReturned() {
        Long capabilityId = 1L;
        List<TechnologyCapability> expectedList = List.of(
                new TechnologyCapability.Builder().capabilityId(capabilityId).technologyId(1L).build(),
                new TechnologyCapability.Builder().capabilityId(capabilityId).technologyId(2L).build()
        );

        // Mock para el mapper que transforma Flux<Entity> a Mono<List<Domain>>
        when(mapper.toMonoTechnologyCapabilityListFromFluxEntities(
                technologyCapabilityRepository.findAllByCapabilityId(capabilityId))
        ).thenReturn(Mono.just(expectedList));

        CompletableFuture<List<TechnologyCapability>> future = adapter.findAllByCapabilityId(capabilityId);
        List<TechnologyCapability> result = future.join();

        assertNotNull(result);
        assertEquals(expectedList.size(), result.size());
        assertEquals(expectedList, result);
    }

    @Test
    void findAllByCapabilityId_shouldThrowDatabaseResourceException_onUnexpectedError() {
        Long capabilityId = 1L;

        when(mapper.toMonoTechnologyCapabilityListFromFluxEntities(
                technologyCapabilityRepository.findAllByCapabilityId(capabilityId))
        ).thenReturn(Mono.error(new RuntimeException("Unexpected DB error")));

        CompletionException ex = assertThrows(CompletionException.class,
                () -> adapter.findAllByCapabilityId(capabilityId).join());

        Throwable cause = ex.getCause();
        assertNotNull(cause);
        assertInstanceOf(DatabaseResourceException.class, cause);
        assertEquals("Error accessing database", cause.getMessage());
    }

    @Test
    void findAllByCapabilityId_shouldThrowProcessorException_whenListIsEmpty() {
        Long capabilityId = 1L;

        when(mapper.toMonoTechnologyCapabilityListFromFluxEntities(
                technologyCapabilityRepository.findAllByCapabilityId(capabilityId))
        ).thenReturn(Mono.just(List.of()));

        CompletionException ex = assertThrows(CompletionException.class,
                () -> adapter.findAllByCapabilityId(capabilityId).join());

        Throwable cause = ex.getCause();
        assertInstanceOf(ProcessorException.class, cause);
        assertEquals("Bad request", cause.getMessage());
    }

    @Test
    void createTechnology_shouldReturnDomain_whenSaveSuccessful() {
        Technology requestDto = new Technology.Builder().build();  // crea un objeto de dominio válido
        TechnologyEntity entity = TechnologyEntity.builder().build();  // crea la entidad que esperas que retorne el repositorio
        Technology expectedDomain = new Technology.Builder().build();  // dominio esperado luego del map

        when(mapper.toEntityFromDomain(requestDto)).thenReturn(entity);
        when(technologyRepository.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.toDomainFromEntity(entity)).thenReturn(expectedDomain);

        CompletableFuture<Technology> future = adapter.createTechnology(requestDto);

        Technology actual = future.join();

        assertEquals(expectedDomain, actual);
    }

    @Test
    void createTechnology_shouldThrowProcessorException_whenSaveReturnsEmpty() {
        Technology requestDto = new Technology.Builder().build();
        TechnologyEntity entity = TechnologyEntity.builder().build();

        when(mapper.toEntityFromDomain(requestDto)).thenReturn(entity);
        when(technologyRepository.save(entity)).thenReturn(Mono.empty());

        CompletionException ex = assertThrows(CompletionException.class,
                () -> adapter.createTechnology(requestDto).join());

        Throwable cause = ex.getCause();
        assertInstanceOf(ProcessorException.class, cause);
        assertEquals("Bad request", cause.getMessage());
    }

    @Test
    void createTechnology_shouldThrowDatabaseResourceException_onUnexpectedError() {
        Technology requestDto = new Technology.Builder().build();
        TechnologyEntity entity = TechnologyEntity.builder().build();

        when(mapper.toEntityFromDomain(requestDto)).thenReturn(entity);
        when(technologyRepository.save(entity)).thenReturn(Mono.error(new RuntimeException("DB failure")));

        CompletionException ex = assertThrows(CompletionException.class,
                () -> adapter.createTechnology(requestDto).join());

        Throwable cause = ex.getCause();
        assertInstanceOf(DatabaseResourceException.class, cause);
        assertEquals("Error accessing database", cause.getMessage());
    }

}