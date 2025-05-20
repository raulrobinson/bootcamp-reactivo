package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.dto.request.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.inbound.mapper.TechnologyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.*;

class ExistsTechnologiesUseCaseTest {

    @Mock
    private TechnologyAdapterPort technologyAdapterPort;

    @Mock
    private TechnologyMapper mapper;

    @InjectMocks
    private ExistsTechnologiesUseCase useCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void existsTechnologies_shouldReturnResponseDtoList_whenTechnologiesExist() {
        // Arrange
        List<Long> ids = List.of(1L, 2L);
        ExistsTechnologiesDto dto = ExistsTechnologiesDto.builder().technologiesIds(ids).build();

        Technology tech1 = Technology.builder().id(1L).name("Java").description("Java Tech").build();
        TechnologyResponseDto responseDto1 = TechnologyResponseDto.builder()
                .id(1L)
                .name("Java")
                .build();

        when(technologyAdapterPort.existsTechnologies(dto)).thenReturn(Flux.just(tech1));
        when(mapper.toResponseTechnologyDto(tech1)).thenReturn(responseDto1);

        // Act & Assert
        StepVerifier.create(useCase.existsTechnologies(dto))
                .expectNext(responseDto1)
                .verifyComplete();

        verify(technologyAdapterPort).existsTechnologies(dto);
        verify(mapper).toResponseTechnologyDto(tech1);
    }

    @Test
    void existsTechnologies_shouldThrowNoContentException_whenNoTechnologiesFound() {
        // Arrange
        List<Long> ids = List.of(99L);
        ExistsTechnologiesDto dto = ExistsTechnologiesDto.builder().technologiesIds(ids).build();

        when(technologyAdapterPort.existsTechnologies(dto)).thenReturn(Flux.empty());

        // Act & Assert
        StepVerifier.create(useCase.existsTechnologies(dto))
                .expectError(NoContentException.class)
                .verify();

        verify(technologyAdapterPort).existsTechnologies(dto);
        verifyNoInteractions(mapper);
    }

}