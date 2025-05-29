package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.CapabilityAdapterPort;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.model.Capability;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.CapabilityEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityBootcampRepository;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.CapabilityRepository;
import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Slf4j
@Service
@RequiredArgsConstructor
public class CapabilityPersistenceAdapter implements CapabilityAdapterPort {

    private final CapabilityRepository capabilityRepository;
    private final CapabilityBootcampRepository capabilityBootcampRepository;
    private final CapabilityEntityMapper mapper;

    @Override
    public CompletionStage<Capability> createCapability(Capability requestDto) {
        return capabilityRepository.existsByName(requestDto.getName())
                .flatMap(exists -> {
                    if (Boolean.TRUE.equals(exists)) {
                        return Mono.error(new DuplicateResourceException(
                                "Already exists",
                                "DUPLICATE_CAPABILITY",
                                requestDto.getName()));
                    }
                    return capabilityRepository.save(mapper.toEntityFromDomain(requestDto));
                })
                .map(mapper::toDomainFromEntity)
                .switchIfEmpty(Mono.error(new ProcessorException(TechnicalMessage.BAD_REQUEST)))
                .toFuture();
    }
}
