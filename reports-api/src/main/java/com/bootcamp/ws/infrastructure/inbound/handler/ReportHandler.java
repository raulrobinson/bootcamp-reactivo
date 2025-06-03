package com.bootcamp.ws.infrastructure.inbound.handler;

import com.bootcamp.ws.domain.model.Report;
import com.bootcamp.ws.domain.spi.ReportServicePort;
import com.bootcamp.ws.infrastructure.inbound.dto.ReportDTO;
import com.bootcamp.ws.infrastructure.inbound.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReportHandler {

    private final ReportServicePort service;
    private final ReportMapper mapper;

    public Mono<ServerResponse> getPdfReport(ServerRequest request) {
        return request.bodyToMono(new ParameterizedTypeReference<List<String>>() {})
                .flatMap(service::generatePdfReport)
                .flatMap(pdfBytes ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_PDF)
                                .header("Content-Disposition", "attachment; filename=report.pdf")
                                .bodyValue(pdfBytes)
                )
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> getAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.getAll(), Report.class);
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.getById(id)
                .flatMap(reporte -> ServerResponse.ok().bodyValue(reporte))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> create(ServerRequest request) {
        return request.bodyToMono(ReportDTO.class)
                .flatMap(dto -> service.create(mapper.toDomain(dto)))
                .flatMap(reporte -> ServerResponse.ok().bodyValue(reporte));
    }

    public Mono<ServerResponse> delete(ServerRequest request) {
        String id = request.pathVariable("id");
        return service.delete(id)
                .then(ServerResponse.noContent().build());
    }
}
