package com.bootcamp.ws.infrastructure.inbound;

import com.bootcamp.ws.infrastructure.inbound.handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(PersonHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/api/v1/persons"), handler::getAll)
                .andRoute(RequestPredicates.GET("/api/v1/persons/{id}"), handler::getById)
                .andRoute(RequestPredicates.POST("/api/v1/persons"), handler::create)
                .andRoute(RequestPredicates.DELETE("/api/v1/persons/{id}"), handler::delete);
    }
}
