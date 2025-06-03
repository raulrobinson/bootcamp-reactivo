package com.bootcamp.ws.infrastructure.adapters.persistence;

import com.bootcamp.ws.domain.api.ReportPersistencePort;
import com.bootcamp.ws.domain.model.Report;
import com.bootcamp.ws.infrastructure.adapters.persistence.mapper.ReportEntityMapper;
import com.bootcamp.ws.infrastructure.adapters.persistence.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReportPersistenceAdapter implements ReportPersistencePort {

    private final ReportRepository reportRepository;
    private final ReportEntityMapper mapper;

    @Override
    public Flux<Report> getAll() {
        return reportRepository.findAll()
                .map(mapper::toDomain)
                .doOnNext(report -> System.out.println("Report found: " + report)) // imprime sin bloquear
                .switchIfEmpty(Flux.error(new RuntimeException("No reports found")));
    }

    @Override
    public Mono<Report> getById(String id) {
        return reportRepository.findById(id)
                .map(mapper::toDomain)
                .switchIfEmpty(Mono.error(new RuntimeException("Report not found with id: " + id)));
    }

    @Override
    public Mono<Report> create(Report reporte) {
        return reportRepository.save(mapper.toEntity(reporte))
                .map(mapper::toDomain)
                .doOnNext(report -> System.out.println("Report created: " + report)) // imprime sin bloquear
                .switchIfEmpty(Mono.error(new RuntimeException("Failed to create report")));
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return reportRepository.deleteById(id)
                .doOnSuccess(aVoid -> System.out.println("Report deleted with id: " + id)) // imprime sin bloquear
                .thenReturn(true)
                .onErrorResume(error -> {
                    System.err.println("Error deleting report: " + error.getMessage());
                    return Mono.just(false);
                });
    }

}
