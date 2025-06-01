package com.bootcamp.ws.infrastructure.inbound;

import com.bootcamp.ws.infrastructure.inbound.dto.request.CapabilityCreateDto;
import com.bootcamp.ws.infrastructure.inbound.handler.CapabilityHandler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class CapabilityRouter {

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/capabilities/find-capabilities",
                    produces = "application/json",
                    method = RequestMethod.POST,
                    beanClass = CapabilityHandler.class,
                    beanMethod = "findCapabilitiesByIdIn",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "findCapabilitiesByIdIn",
                            summary = "Find Capabilities by IDs",
                            description = "Fetches Capabilities by their IDs from the database",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "List of Capability IDs",
                                    content = @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(
                                                    type = "array",
                                                    example = "[1,2,3]"
                                            )
                                    )
                            )
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/capabilities",
                    produces = "application/json",
                    method = RequestMethod.POST,
                    beanClass = CapabilityHandler.class,
                    beanMethod = "createCapability",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "createCapability",
                            summary = "Create a new Capability",
                            description = "Create a new Capability in the database",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "Capability Request DTO",
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CapabilityCreateDto.class)
                                    )
                            )
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/capabilities/find-capability/{capabilityId}",
                    produces = "application/json",
                    method = RequestMethod.GET,
                    beanClass = CapabilityHandler.class,
                    beanMethod = "findCapabilityById",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "findCapabilityById",
                            summary = "Find Capability by ID",
                            description = "Fetches a Capability by its ID from the database",
                            parameters = {
                                    @Parameter(name = "capabilityId", in = ParameterIn.PATH, description = "Capability ID", example = "1"),
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routes(CapabilityHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/capabilities", handler::createCapability)
                .POST("/api/v1/capabilities/find-capabilities", handler::findCapabilitiesByIdIn)
                .GET("/api/v1/capabilities/find-capability/{capabilityId}", handler::findCapabilityById)
                .build();
    }
}
