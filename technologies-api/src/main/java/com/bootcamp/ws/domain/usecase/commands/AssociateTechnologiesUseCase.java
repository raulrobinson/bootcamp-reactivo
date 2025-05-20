package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.common.enums.TechnicalMessage;
import com.bootcamp.ws.domain.common.exceptions.DuplicateException;
import com.bootcamp.ws.domain.common.exceptions.NoContentException;
import com.bootcamp.ws.domain.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;
import reactor.core.publisher.Mono;

import java.util.List;

public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public AssociateTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

    @Override
    public Mono<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto) {
        return technologyAdapterPort.existsByCapabilityId(dto.getCapabilityId())
                .flatMap(exists -> {
                            if (!exists) return technologyAdapterPort.associateTechnologies(dto);
                            return Mono.error(new DuplicateException(TechnicalMessage.ALREADY_EXISTS));
                        })
                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
    }
}
