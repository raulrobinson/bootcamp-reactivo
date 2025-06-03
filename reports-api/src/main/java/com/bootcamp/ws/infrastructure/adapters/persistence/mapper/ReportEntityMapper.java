package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.model.Report;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.ReportEntity;
import org.springframework.stereotype.Component;

@Component
public class ReportEntityMapper {

    public ReportEntity toEntity(Report report) {
        if (report == null) return null;
        return ReportEntity.builder()
                .id(report.getId())
                .title(report.getTitle())
                .content(report.getContent())
                .generatedAt(report.getGeneratedAt())
                .build();
    }

    public Report toDomain(ReportEntity reportEntity) {
        if (reportEntity == null) return null;
        return Report.builder()
                .id(reportEntity.getId())
                .title(reportEntity.getTitle())
                .content(reportEntity.getContent())
                .generatedAt(reportEntity.getGeneratedAt())
                .build();
    }
}
