package com.bootcamp.ws.infrastructure.adapters.persistence.entity;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("technologies")
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyEntity extends Auditable {

    @Id
    private Long id;

    @NotNull
    @Length(min=3, max=50)
    private String name;

    @NotNull
    @Length(min=10, max=90)
    private String description;
}
