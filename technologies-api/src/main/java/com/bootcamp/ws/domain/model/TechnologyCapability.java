package com.bootcamp.ws.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyCapability {
    private Long technologyId;
    private Long capabilityId;
}
