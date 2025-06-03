package com.bootcamp.ws.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Report {
    private String id;
    private String title;
    private String content;
    private LocalDateTime generatedAt;
}
