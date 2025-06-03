package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.model.Report;
import com.bootcamp.ws.infrastructure.inbound.dto.ReportDTO;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    public Report toDomain(ReportDTO dto) {
        if (dto == null) return null;
        return Report.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .build();
    }
}
