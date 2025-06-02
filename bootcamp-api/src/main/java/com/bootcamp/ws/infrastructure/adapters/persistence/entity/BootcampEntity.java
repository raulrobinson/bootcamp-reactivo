package com.bootcamp.ws.infrastructure.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("bootcamp")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BootcampEntity {
    @Id
    private Long id;
    private String name;
    private String description;
}
