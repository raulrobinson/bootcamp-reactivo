package com.bootcamp.ws.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Report {
    private String id;
    private String title;
    private String description;
    private String status;
}
