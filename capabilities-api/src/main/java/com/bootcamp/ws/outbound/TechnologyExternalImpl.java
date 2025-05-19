package com.bootcamp.ws.outbound;

import com.bootcamp.ws.common.enums.TechnicalMessage;
import com.bootcamp.ws.common.exceptions.ProcessorException;
import com.bootcamp.ws.outbound.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.outbound.dto.TechnologyDto;
import com.bootcamp.ws.outbound.model.TechnologyAssociateTechnologies;
import com.bootcamp.ws.outbound.model.TechnologyCapability;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TechnologyExternalImpl implements TechnologyExternal {

    private final WebClient client;

    @Value("${technologies.service.url}") String serviceUrl;

    @Override
    public Mono<List<TechnologyDto>> existsTechnologies(ExistsTechnologiesDto dto) {
        System.out.println("ids external body: = " + dto);
        return client.post()
                .uri(serviceUrl + "/exists")
                .bodyValue(dto)
                .retrieve()
                .bodyToFlux(TechnologyDto.class)
                .collectList()
                .onErrorResume(throwable -> Mono.error(new ProcessorException(throwable, TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS)));
    }

    @Override
    public Mono<List<TechnologyCapability>> associateTechnologies(TechnologyAssociateTechnologies request) {
        return client.post()
                .uri(serviceUrl + "/associate-technologies")
                .bodyValue(request)
                .retrieve()
                .bodyToFlux(TechnologyCapability.class)
                .collectList()
                .onErrorResume(throwable -> Mono.error(new ProcessorException(throwable, TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS)));
    }

}
