package com.bootcamp.ws.domain.usecase.queries;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.domain.spi.FindTechnologiesByIdsServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindTechnologiesByIdsUseCase implements FindTechnologiesByIdsServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    @Override
    public Mono<List<TechnologyResponseDto>> findTechnologiesByIds(List<Long> technologiesIds) {
        return technologyAdapterPort.findTechnologiesByIds(technologiesIds)
                .collectList()
                .map(technologies -> technologies.stream()
                        .map(technology -> TechnologyResponseDto.builder()
                                .id(technology.getId())
                                .name(technology.getName())
                                .build())
                        .toList());
    }
}
