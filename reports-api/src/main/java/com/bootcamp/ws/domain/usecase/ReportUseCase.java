package com.bootcamp.ws.domain.usecase;

import com.bootcamp.ws.domain.api.ReportPersistencePort;
import com.bootcamp.ws.domain.model.Report;
import com.bootcamp.ws.domain.spi.ReportServicePort;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReportUseCase implements ReportServicePort {

    private final ReportPersistencePort reportPersistence;

    public ReportUseCase(ReportPersistencePort reportPersistence) {
        this.reportPersistence = reportPersistence;
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
