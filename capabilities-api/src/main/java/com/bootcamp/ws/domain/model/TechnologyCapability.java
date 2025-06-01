package com.bootcamp.ws.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyCapability {
    private Long technologyId;
    private Long capabilityId;
}
