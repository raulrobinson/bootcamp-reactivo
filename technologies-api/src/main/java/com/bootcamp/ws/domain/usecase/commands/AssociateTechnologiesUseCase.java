package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;
import com.bootcamp.ws.infrastructure.inbound.mapper.TechnologyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;
    private final TechnologyMapper mapper;

    @Override
    public Mono<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateDto dto) {
        return technologyAdapterPort.associateTechnologies(dto)
                .flatMap(technologyCapabilityEntity -> {
                    if (technologyCapabilityEntity != null) {
                        return Mono.just(mapper.toAssociateTechnologiesResponseDto(technologyCapabilityEntity));
                    } else {
                        return Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT));
                    }
                })
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
    }
}
