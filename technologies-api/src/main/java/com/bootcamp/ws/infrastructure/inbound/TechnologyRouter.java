package com.bootcamp.ws.infrastructure.inbound;

import com.bootcamp.ws.infrastructure.common.handler.ErrorDTO;
import com.bootcamp.ws.infrastructure.inbound.dto.request.AssociateTechnologiesCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.ExistsTechnologiesRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.request.TechnologyCreateRequestDto;
import com.bootcamp.ws.infrastructure.inbound.dto.response.AssociateTechnologiesResponseDto;
import com.bootcamp.ws.infrastructure.inbound.dto.response.TechnologyResponseDto;
import com.bootcamp.ws.infrastructure.inbound.dto.response.doc.CapabilityWithTechnologiesResponseDto;
import com.bootcamp.ws.infrastructure.inbound.handler.TechnologyHandler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
                    path = "/api/v1/technologies/find-technologies",
                    produces = "application/json",
                    method = RequestMethod.POST,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "findTechnologiesByIdIn",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "findTechnologiesByIdIn",
                            summary = "Find technologies by IDs",
                            description = "Fetches technologies by their IDs from the database",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "List of technology IDs",
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ExistsTechnologiesRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "200",
                                            description = "OK",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TechnologyResponseDto.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "404", description = "Not Found",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "400", description = "Bad Request",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "500", description = "Internal Server Error",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    )
                            }
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
                            description = "Create a new technology in the database.",
                            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                                    required = true,
                                    description = "Technology Request DTO",
                                    content = @io.swagger.v3.oas.annotations.media.Content(
                                            mediaType = "application/json",
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TechnologyCreateRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "201",
                                            description = "Created",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = TechnologyResponseDto.class)
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "400",
                                            description = "Bad Request",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "409",
                                            description = "Conflict",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "500",
                                            description = "Internal Server Error",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    )
                            }
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
                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AssociateTechnologiesCreateRequestDto.class)
                                    )
                            ),
                            responses = {
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "200",
                                            description = "OK",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = AssociateTechnologiesResponseDto.class)
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "400", description = "Bad Request",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "500", description = "Internal Server Error",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/technologies/find-associates-technologies-by-cap-id/{capabilityId}",
                    produces = "application/json",
                    method = RequestMethod.GET,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "findAssociatesTechsByCapId",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "findAssociatesTechsByCapId",
                            summary = "Find associated technologies by capability ID",
                            description = "Fetches associated technologies by capability ID from the database",
                            parameters = {
                                    @Parameter(name = "capabilityId", in = ParameterIn.PATH, description = "Capability ID", example = "1"),
                            },
                            responses = {
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "200",
                                            description = "OK",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = CapabilityWithTechnologiesResponseDto.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "404", description = "Not Found",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "400", description = "Bad Request",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "500", description = "Internal Server Error",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    )
                            }
                    )
            ),
            @RouterOperation(
                    path = "/api/v1/technologies/delete-assoc/{capabilityId}",
                    produces = "application/json",
                    method = RequestMethod.DELETE,
                    beanClass = TechnologyHandler.class,
                    beanMethod = "deleteAssocTechnologiesByCapabilityId",
                    operation = @io.swagger.v3.oas.annotations.Operation(
                            operationId = "deleteAssocTechnologiesByCapabilityId",
                            summary = "Delete technologies-capability by capability ID",
                            description = "Deletes technologies associated with a capability ID from the database",
                            parameters = {
                                    @Parameter(name = "capabilityId", in = ParameterIn.PATH, description = "Capability ID", example = "1"),
                            },
                            responses = {
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "202",
                                            description = "Accepted"
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "404", description = "Not Found",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    ),
                                    @io.swagger.v3.oas.annotations.responses.ApiResponse(
                                            responseCode = "500", description = "Internal Server Error",
                                            content = @io.swagger.v3.oas.annotations.media.Content(
                                                    mediaType = "application/json",
                                                    array = @io.swagger.v3.oas.annotations.media.ArraySchema(
                                                            schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ErrorDTO.class)
                                                    )
                                            )
                                    )
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> route(TechnologyHandler handler) {
        return RouterFunctions.route()
                .POST("/api/v1/technologies", handler::createTechnology)
                .POST("/api/v1/technologies/find-technologies", handler::findTechnologiesByIdIn)
                .POST("/api/v1/technologies/associate-technologies", handler::associateTechnologies)
                .GET("/api/v1/technologies/find-associates-technologies-by-cap-id/{capabilityId}", handler::findAssociatesTechsByCapId)
                .DELETE("/api/v1/technologies/delete-assoc/{capabilityId}", handler::deleteAssocTechnologiesByCapabilityId)
                .build();
    }
}
