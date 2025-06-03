package com.bootcamp.ws.infrastructure.adapters.outbound;

import com.bootcamp.ws.domain.api.CapabilityExternalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CapabilityExternalAdapter implements CapabilityExternalPort {

    private final WebClient client;

    @Value("${capabilities.service.url}") String capabilitiesServiceUrl;
}
