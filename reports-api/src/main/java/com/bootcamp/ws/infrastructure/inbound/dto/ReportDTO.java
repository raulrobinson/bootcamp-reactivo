package com.bootcamp.ws.infrastructure.inbound.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReportDTO {
    private String title;
    private String content;
}
