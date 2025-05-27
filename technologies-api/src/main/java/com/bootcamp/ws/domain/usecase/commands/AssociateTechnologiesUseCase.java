package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;

public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public AssociateTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }

//    @Override
//    public Mono<List<TechnologyCapability>> associateTechnologies(AssociateTechnologiesCreateRequestDto dto) {
//        return technologyAdapterPort.existsByCapabilityId(dto.getCapabilityId())
//                .flatMap(exists -> {
//                            if (!exists) return technologyAdapterPort.associateTechnologies(dto);
//                            return Mono.error(new DuplicateException(TechnicalMessage.ALREADY_EXISTS));
//                        })
//                .switchIfEmpty(Mono.error(new NoContentException(TechnicalMessage.NO_CONTENT)));
//    }
}
