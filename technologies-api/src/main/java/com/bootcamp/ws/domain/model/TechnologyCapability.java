package com.bootcamp.ws.domain.model;

import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyCapability {
    private Long technologyId;
    private Long capabilityId;
}
