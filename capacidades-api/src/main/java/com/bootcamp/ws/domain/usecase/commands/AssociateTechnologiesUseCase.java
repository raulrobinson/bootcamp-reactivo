package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;

public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyAdapterPort technologyAdapterPort;

    public AssociateTechnologiesUseCase(TechnologyAdapterPort technologyAdapterPort) {
        this.technologyAdapterPort = technologyAdapterPort;
    }
}
