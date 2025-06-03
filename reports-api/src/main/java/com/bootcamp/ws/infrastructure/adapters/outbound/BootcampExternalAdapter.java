package com.bootcamp.ws.infrastructure.adapters.outbound;

import com.bootcamp.ws.domain.api.BootcampExternalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class BootcampExternalAdapter implements BootcampExternalPort {

    private final WebClient client;

    @Value("${bootcamp.service.url}") String bootcampServiceUrl;
}
