package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.model.CapabilityFullList;
import com.bootcamp.ws.domain.model.Capability;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CapabilityMapper {

    public Mono<CapabilityFullList> toDtoFromDomain(Mono<Capability> domain) {
        return domain.map(capability -> CapabilityFullList.builder()
                .id(capability.getId())
                .name(capability.getName())
                //.technologies(capability.getTechnologies())
                .build());
    }
}
