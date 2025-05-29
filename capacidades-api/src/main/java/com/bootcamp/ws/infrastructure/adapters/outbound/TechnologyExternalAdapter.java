package com.bootcamp.ws.infrastructure.adapters.outbound;

import com.bootcamp.ws.domain.api.TechnologyAdapterPort;
import com.bootcamp.ws.domain.model.AssociatesTechsByCapId;
import com.bootcamp.ws.domain.model.Technology;
import com.bootcamp.ws.infrastructure.adapters.outbound.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.domain.model.TechnologyCapability;
import com.bootcamp.ws.infrastructure.common.enums.TechnicalMessage;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class TechnologyExternalAdapter implements TechnologyAdapterPort {

    private final WebClient client;

    @Value("${technologies.service.url}") String serviceUrl;

    @Override
    public CompletableFuture<List<Technology>> existsTechnologies(List<Long> technologiesIds) {
        return client.post()
                .uri(serviceUrl + "/exists")
                .bodyValue(technologiesIds)
                .retrieve()
                .bodyToFlux(Technology.class)
                .collectList()
                .onErrorResume(throwable -> Mono.error(new ProcessorException(TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS, throwable)))
                .toFuture();
    }

    @Override
    public CompletableFuture<List<TechnologyCapability>> associateTechnologies(Long capabilityId, List<Long> technologiesIds) {
        return client.post()
                .uri(serviceUrl + "/associate-technologies")
                .bodyValue(TechnologyAssociateTechnologies.builder()
                        .capabilityId(capabilityId)
                        .technologiesIds(technologiesIds)
                        .build())
                .retrieve()
                .bodyToFlux(TechnologyCapability.class)
                .collectList()
                .onErrorResume(throwable -> Mono.error(new ProcessorException(TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS, throwable)))
                .toFuture();
    }

    @Override
    public CompletableFuture<List<AssociatesTechsByCapId>> findAssociatesTechsByCapId(Long capabilityId) {
        return client.get()
                .uri(serviceUrl + "/find-associates-technologies-by-cap-id/{capabilityId}", capabilityId)
                .retrieve()
                .bodyToFlux(AssociatesTechsByCapId.class)
                .collectList()
                .onErrorResume(throwable -> Mono.error(new ProcessorException(TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS, throwable)))
                .toFuture();
    }
}
