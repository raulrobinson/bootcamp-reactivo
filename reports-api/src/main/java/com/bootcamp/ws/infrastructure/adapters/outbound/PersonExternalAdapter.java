package com.bootcamp.ws.infrastructure.adapters.outbound;

import com.bootcamp.ws.domain.api.PersonExternalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class PersonExternalAdapter implements PersonExternalPort {

    private final WebClient client;

    @Value("${persons.service.url}") String personsServiceUrl;
}
