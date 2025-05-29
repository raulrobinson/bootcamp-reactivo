package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.CapabilityAdapterPort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityBootcampRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CapabilityPersistenceAdapter implements CapabilityAdapterPort {

    private final CapabilityRepository capabilityRepository;
    private final CapabilityBootcampRepository capabilityBootcampRepository;
    private final CapabilityEntityMapper capabilityEntityMapper;

}
