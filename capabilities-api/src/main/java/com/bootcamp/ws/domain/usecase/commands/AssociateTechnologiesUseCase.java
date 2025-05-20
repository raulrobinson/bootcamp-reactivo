package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;

public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyExternalAdapterPort technologyExternalAdapterPort;

    public AssociateTechnologiesUseCase(TechnologyExternalAdapterPort technologyExternalAdapterPort) {
        this.technologyExternalAdapterPort = technologyExternalAdapterPort;
    }
}
