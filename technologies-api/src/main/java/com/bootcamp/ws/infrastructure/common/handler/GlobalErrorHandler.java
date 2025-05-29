package com.bootcamp.ws.infrastructure.common.handler;

import com.bootcamp.ws.domain.exception.BusinessException;
import com.bootcamp.ws.domain.exception.DuplicateResourceException;
import com.bootcamp.ws.domain.exception.enums.TechnicalMessage;
import com.bootcamp.ws.infrastructure.common.exception.NoContentException;
import com.bootcamp.ws.infrastructure.common.exception.ProcessorException;
import com.bootcamp.ws.infrastructure.common.exception.TechnicalException;
import com.bootcamp.ws.infrastructure.inbound.dto.response.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;

@Component
@Slf4j
@Order(-2)
public class GlobalErrorHandler {

    public Mono<ServerResponse> handle(Throwable throwable) {
        log.error("Exception captured globally: {}", throwable.toString());

        return switch (throwable) {
            case BusinessException ex -> buildErrorResponse(
                    HttpStatus.BAD_REQUEST,
                    TechnicalMessage.BAD_REQUEST,
                    List.of(ErrorDto.of(
                            TechnicalMessage.BAD_REQUEST.getCode(),
                            ex.getMessage(),
                            ex.getParameter(),
                            Instant.now().toString()
                    ))
            );

            case DuplicateResourceException ex -> buildErrorResponse(
                    HttpStatus.CONFLICT,
                    TechnicalMessage.ALREADY_EXISTS,
                    List.of(ErrorDto.of(
                            TechnicalMessage.ALREADY_EXISTS.getCode(),
                            ex.getMessage(),
                            ex.getParameter(),
                            Instant.now().toString()
                    ))
            );

            case NoContentException ignored -> ServerResponse.noContent().build();

            case ProcessorException ex -> buildErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    TechnicalMessage.INTERNAL_SERVER_ERROR,
                    List.of(ErrorDto.of(
                            TechnicalMessage.INTERNAL_SERVER_ERROR.getCode(),
                            ex.getMessage(),
                            TechnicalMessage.INTERNAL_SERVER_ERROR.getParameter(),
                            Instant.now().toString()
                    ))
            );

            case TechnicalException ex -> buildErrorResponse(
                    HttpStatus.SERVICE_UNAVAILABLE,
                    TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS,
                    List.of(ErrorDto.of(
                            TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS.getCode(),
                            ex.getMessage(),
                            TechnicalMessage.INTERNAL_ERROR_IN_ADAPTERS.getParameter(),
                            Instant.now().toString()
                    ))
            );

            default -> buildErrorResponse(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    TechnicalMessage.INTERNAL_SERVER_ERROR,
                    List.of(ErrorDto.of(
                            TechnicalMessage.INTERNAL_SERVER_ERROR.getCode(),
                            throwable.getMessage(),
                            TechnicalMessage.INTERNAL_SERVER_ERROR.getParameter(),
                            Instant.now().toString()
                    ))
            );
        };
    }

    private Mono<ServerResponse> buildErrorResponse(HttpStatus status,
                                                    TechnicalMessage technicalMessage,
                                                    List<ErrorDto> errors) {
        return ServerResponse.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(errors);
    }
}
