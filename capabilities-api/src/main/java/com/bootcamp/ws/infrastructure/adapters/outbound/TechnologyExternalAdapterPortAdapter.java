package com.bootcamp.ws.infrastructure.adapters.outbound;

import com.bootcamp.ws.domain.api.TechnologyExternalAdapterPort;
import com.bootcamp.ws.domain.dto.ExistsTechnologies;
import com.bootcamp.ws.domain.dto.Response;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.domain.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TechnologyExternalAdapterPortAdapter implements TechnologyExternalAdapterPort {

    private final WebClient client;

    @Value("${technologies.service.url}") String serviceUrl;

    @Override
    public Mono<List<Technology>> findTechnologiesByIdIn(ExistsTechnologies dto) {
        return client.post()
                .uri(serviceUrl + "/find-technologies")
                .bodyValue(dto)
                .retrieve()
                .bodyToFlux(Technology.class)
                .collectList()
                .onErrorResume(throwable -> Mono.error(new ProcessorException(TechnicalMessage
                        .INTERNAL_ERROR_IN_ADAPTERS, throwable)));
    }

    @Override
    public Mono<List<TechnologyCapability>> associateTechnologies(TechnologyAssociateTechnologies request) {
        return client.post()
                .uri(serviceUrl + "/associate-technologies")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(TechnologyCapability.class)
                .collectList()
                .onErrorResume(throwable -> Mono.error(new ProcessorException(TechnicalMessage
                        .INTERNAL_ERROR_IN_ADAPTERS, throwable)));
    }

    @Override
    public Mono<Response> findAssociatesTechsByCapId(Long capabilityId) {
        return client.get()
                .uri(serviceUrl + "/find-associates-technologies-by-cap-id/{capabilityId}", capabilityId)
                .retrieve()
                .bodyToMono(Response.class)
                .onErrorResume(throwable -> Mono.error(new ProcessorException(TechnicalMessage
                        .INTERNAL_ERROR_IN_ADAPTERS, throwable)));
    }

    @Override
    public Mono<Boolean> deleteAssocTechnologiesByCapabilityId(Long capabilityId) {
        return client.delete()
                .uri(serviceUrl + "/delete-assoc/{capabilityId}", capabilityId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(throwable -> Mono.error(new ProcessorException(TechnicalMessage
                        .INTERNAL_ERROR_IN_ADAPTERS, throwable)));
    }
}
