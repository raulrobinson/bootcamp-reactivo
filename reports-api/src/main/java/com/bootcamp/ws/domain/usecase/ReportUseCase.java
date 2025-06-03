package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.*;
import com.bootcamp.ws.domain.dto.ExistsTechnologies;
import com.bootcamp.ws.domain.model.Report;
import com.bootcamp.ws.domain.spi.ReportServicePort;
import com.bootcamp.ws.infrastructure.util.PdfGenerator;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class ReportUseCase implements ReportServicePort {

    private final ReportPersistencePort reportPersistence;
    private final BootcampExternalPort bootcampExternalPort;
    private final CapabilityExternalPort capabilityExternalPort;
    private final TechnologyExternalPort technologyExternalPort;
    private final PersonExternalPort personExternalPort;

    public ReportUseCase(ReportPersistencePort reportPersistence, BootcampExternalPort bootcampExternalPort, CapabilityExternalPort capabilityExternalPort, TechnologyExternalPort technologyExternalPort, PersonExternalPort personExternalPort) {
        this.reportPersistence = reportPersistence;
        this.bootcampExternalPort = bootcampExternalPort;
        this.capabilityExternalPort = capabilityExternalPort;
        this.technologyExternalPort = technologyExternalPort;
        this.personExternalPort = personExternalPort;
    }

    public Mono<Report> generateReport(List<String> dto) {
        List<Long> ids = dto.stream()
                .map(Long::parseLong)
                .toList();
        return technologyExternalPort.findTechnologiesByIdIn(ExistsTechnologies.builder().technologiesIds(ids).build())
                .flatMap(technologies -> {
                    if (technologies.isEmpty()) {
                        return Mono.error(new RuntimeException("No technologies found for the provided IDs"));
                    }
                    return Mono.empty();
                });
    }

    @Override
    public Mono<byte[]> generatePdfReport(List<String> dto) {
        return generateReport(dto)
                .map(report -> PdfGenerator.generatePdf(report.getTitle(), report.getContent()));
    }

    @Override
    public Flux<Report> getAll() {
        return reportPersistence.getAll()
                .doOnNext(report -> System.out.println("Report found: " + report)) // imprime sin bloquear
                .switchIfEmpty(Flux.error(new RuntimeException("No reports found")));
    }

    @Override
    public Mono<Report> getById(String id) {
        return reportPersistence.getById(id)
                .doOnNext(report -> System.out.println("Report found: " + report)) // imprime sin bloquear
                .switchIfEmpty(Mono.error(new RuntimeException("Report not found with id: " + id)));
    }

    @Override
    public Mono<Report> create(Report report) {
        return reportPersistence.create(report)
                .doOnNext(createdReport -> System.out.println("Report created: " + createdReport)) // imprime sin bloquear
                .switchIfEmpty(Mono.error(new RuntimeException("Failed to create report")));
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return reportPersistence.delete(id)
                .doOnNext(deleted -> {
                    if (deleted) {
                        System.out.println("Report deleted with id: " + id);
                    } else {
                        System.out.println("Report not found for deletion with id: " + id);
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Failed to delete report with id: " + id)));
    }
}
