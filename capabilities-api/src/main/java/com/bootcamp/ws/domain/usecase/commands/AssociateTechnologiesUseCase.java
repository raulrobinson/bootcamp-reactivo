package com.bootcamp.ws.domain.usecase.commands;

import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.domain.spi.AssociateTechnologiesServicePort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssociateTechnologiesUseCase implements AssociateTechnologiesServicePort {

    private final TechnologyExternalAdapterPort technologyExternalAdapterPort;
    private final CapabilityEntityMapper mapper;

}
