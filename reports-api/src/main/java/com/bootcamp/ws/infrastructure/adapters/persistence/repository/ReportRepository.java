package com.bootcamp.ws.infrastructure.adapters.persistence.repository;

import com.bootcamp.ws.infrastructure.adapters.persistence.entity.ReportEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ReportRepository extends ReactiveCrudRepository<ReportEntity, String> {
}
