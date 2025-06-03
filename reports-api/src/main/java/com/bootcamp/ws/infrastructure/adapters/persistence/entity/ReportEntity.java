package com.bootcamp.ws.infrastructure.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reports")
public class ReportEntity {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime generatedAt;
}
