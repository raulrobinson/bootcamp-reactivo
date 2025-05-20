package com.bootcamp.ws.infrastructure.inbound;

import com.bootcamp.ws.infrastructure.inbound.handler.TestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TestRouter {

     @Bean
     public RouterFunction<ServerResponse> route(TestHandler handler) {
         return RouterFunctions.route()
                 .POST("/test/capabilities/existsTechnologiesByIds", handler::existsTechnologiesInTechnologiesApi)
                 .GET("/test/capabilities/find-associates-technologies-by-cap-id/{capabilityId}", handler::findAssocTechsByCapId)
                 .build();
     }
}
