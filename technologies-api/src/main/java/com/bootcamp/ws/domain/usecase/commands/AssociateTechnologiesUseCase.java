package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.dto.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.TechnologyCapabilityEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    @Override
    public Mono<List<TechnologyCapabilityEntity>> associateTechnologies(AssociateTechnologiesCreateDto dto) {
        return technologyAdapterPort.associateTechnologies(dto)
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
    }
}
