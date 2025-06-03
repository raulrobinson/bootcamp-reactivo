package com.bootcamp.ws.infrastructure.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reportes")
public class ReportEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private String status;
}
