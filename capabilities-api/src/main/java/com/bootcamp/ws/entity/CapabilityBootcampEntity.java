package com.bootcamp.ws.entity;

import lombok.*;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("capabilities_bootcamps")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapabilityBootcampEntity {
    @Column("capability_id")
    private Long capabilityId;

    @Column("bootcamp_id")
    private Long bootcampId;
}

