package com.bootcamp.ws.infrastructure.adapters.persistence.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("technologies_capabilities")
@EqualsAndHashCode(callSuper=false)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyCapabilityEntity {

    @Column("technology_id")
    private Long technologyId;

    @Column("capability_id")
    private Long capabilityId;
}
