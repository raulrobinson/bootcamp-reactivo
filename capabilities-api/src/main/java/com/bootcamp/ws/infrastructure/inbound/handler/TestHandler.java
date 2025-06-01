package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.infrastructure.inbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestHandler {

    private final TechnologyExternalAdapterPort technologyExternalAdapterPort;

    public Mono<List<Technology>> existsTechnologies(ExistsTechnologiesDto dto) {
        return technologyExternalAdapterPort.findTechnologiesByIdIn(dto)
                .doOnNext(technologyDtos -> log.info("Technologies found: {}", technologyDtos))
                .doOnError(error -> log.error("Error occurred while checking technologies: {}", error.getMessage()));
    }

    public Mono<List<TechnologyCapability>> associateTechnologies(TechnologyAssociateTechnologies request) {
        return technologyExternalAdapterPort.associateTechnologies(request)
                .doOnNext(technologyCapabilities -> log.info("Technologies associated: {}", technologyCapabilities))
                .doOnError(error -> log.error("Error occurred while associating technologies: {}", error.getMessage()));
    }

//    public Mono<List<FindAssociatesTechsByCapIdResponseDto>> findAssociatesTechsByCapId(Long capabilityId) {
//        return technologyExternalAdapterPort.findAssociatesTechsByCapId(capabilityId)
//                .doOnNext(capabilityWithTechnologiesDto -> log.info("Capability with technologies found: {}", capabilityWithTechnologiesDto))
//                .doOnError(error -> log.error("Error occurred while finding associated technologies: {}", error.getMessage()));
//    }

    public Mono<ServerResponse> existsTechnologiesInTechnologiesApi(ServerRequest request) {
        return request.bodyToMono(new ParameterizedTypeReference<List<Long>>() {})
                .flatMapMany(req -> existsTechnologies(new ExistsTechnologiesDto(req)))
                .collectList()
                .flatMap(technologies -> {
                    return ServerResponse.ok().bodyValue(technologies); // 200 OK con lista
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }

//    public Mono<ServerResponse> findAssocTechsByCapId(ServerRequest request) {
//        Long capabilityId = Long.parseLong(request.pathVariable("capabilityId"));
//        return findAssociatesTechsByCapId(capabilityId)
//                .flatMap(technology -> ServerResponse.ok().bodyValue(technology))
//                .switchIfEmpty(ServerResponse.notFound().build());
//    }
}
