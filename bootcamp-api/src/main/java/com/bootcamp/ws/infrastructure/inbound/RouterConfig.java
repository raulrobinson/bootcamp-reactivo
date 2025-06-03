package com.bootcamp.ws.infrastructure.inbound;

import com.bootcamp.ws.infrastructure.inbound.handler.BootcampHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> route(BootcampHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/api/v1/bootcamp"), handler::getAll)
                .andRoute(RequestPredicates.GET("/api/v1/bootcamp/{id}"), handler::getById)
                .andRoute(RequestPredicates.POST("/api/v1/bootcamp"), handler::create)
                .andRoute(RequestPredicates.DELETE("/api/v1/bootcamp/{id}"), handler::delete);
    }
}
