package com.bootcamp.ws.infrastructure.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("technologies")
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyEntity {
    @Id
    private Long id;
    private String name;
    private String description;
}
