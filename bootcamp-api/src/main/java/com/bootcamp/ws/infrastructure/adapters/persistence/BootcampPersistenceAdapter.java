package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.BootcampPersistenceAdapterPort;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.BootcampEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.BootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BootcampPersistenceAdapter implements BootcampPersistenceAdapterPort {

    private final BootcampRepository bootcampRepository;
    private final BootcampEntityMapper bootcampEntityMapper;

}
