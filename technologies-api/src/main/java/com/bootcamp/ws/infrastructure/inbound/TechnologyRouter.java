package com.bootcamp.ws.infrastructure.inbound;

import com.bootcamp.ws.domain.dto.AssociateTechnologiesCreateDto;
import com.bootcamp.ws.domain.dto.ExistsTechnologiesDto;
import com.bootcamp.ws.domain.dto.TechnologyCreateDto;
import com.bootcamp.ws.infrastructure.inbound.handler.TechnologyHandler;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class TechnologyRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/technologies/exists",
                    produces = "application/json",
                    method = RequestMethod.POST,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "existsTechnologies",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "existsTechnologies",
                            summary = "Find technologies by IDs",
                            description = "Fetches technologies by their IDs from the database",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "List of technology IDs",
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExistsTechnologiesDto.class)
                                    )
                            )
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/technologies",
                    produces = "application/json",
                    method = RequestMethod.POST,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "createTechnology",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "createTechnology",
                            summary = "Create a new technology",
                            description = "Create a new technology in the database",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "Technology Request DTO",
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TechnologyCreateDto.class)
                                    )
                            )
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/technologies/associate-technologies",
                    produces = "application/json",
                    method = RequestMethod.POST,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "associateTechnologies",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "associateTechnologies",
                            summary = "Associate technologies with capabilities",
                            description = "Associate technologies with capabilities in the database",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "Technology IDs Request DTO",
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AssociateTechnologiesCreateDto.class)
                                    )
                            )
                    )
            )
    })
    public RouterFunction<ServerResponse> route(TechnologyHandler technologyHandler) {
        return RouterFunctions.route()
                .POST("/api/v1/technologies", technologyHandler::createTechnology)
                .POST("/api/v1/technologies/exists", technologyHandler::existsTechnologies)
                .POST("/api/v1/technologies/associate-technologies", technologyHandler::associateTechnologies)
                .build();
    }
}
