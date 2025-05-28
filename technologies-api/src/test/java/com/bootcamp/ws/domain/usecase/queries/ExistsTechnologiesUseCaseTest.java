package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.infrastructure.inbound.mapper.TechnologyMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

//    @Test
//    void existsTechnologies_shouldReturnResponseDtoList_whenTechnologiesExist() {
//        // Arrange
//        List<Long> ids = List.of(1L, 2L);
//        ExistsTechnologiesRequestDto dto = ExistsTechnologiesRequestDto.builder().technologiesIds(ids).build();
//
//        Technology tech1 = Technology.builder().id(1L).name("Java").description("Java Tech").build();
//        TechnologyResponseDto responseDto1 = TechnologyResponseDto.builder()
//                .id(1L)
//                .name("Java")
//                .build();
//
//        when(technologyAdapterPort.existsTechnologies(dto)).thenReturn(Flux.just(tech1));
//        when(mapper.toResponseTechnologyDto(tech1)).thenReturn(responseDto1);
//
//        // Act & Assert
//        StepVerifier.create(useCase.existsTechnologies(dto))
//                .expectNext(responseDto1)
//                .verifyComplete();
//
//        verify(technologyAdapterPort).existsTechnologies(dto);
//        verify(mapper).toResponseTechnologyDto(tech1);
//    }

//    @Test
//    void existsTechnologies_shouldThrowNoContentException_whenNoTechnologiesFound() {
//        // Arrange
//        List<Long> ids = List.of(99L);
//        ExistsTechnologiesRequestDto dto = ExistsTechnologiesRequestDto.builder().technologiesIds(ids).build();
//
//        when(technologyAdapterPort.existsTechnologies(dto)).thenReturn(Flux.empty());
//
//        // Act & Assert
//        StepVerifier.create(useCase.existsTechnologies(dto))
//                .expectError(NoContentException.class)
//                .verify();
//
//        verify(technologyAdapterPort).existsTechnologies(dto);
//        verifyNoInteractions(mapper);
//    }

}