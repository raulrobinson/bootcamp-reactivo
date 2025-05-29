package com.bootcamp.ws.infrastructure.adapters.outbound.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyAssociateTechnologies {
    private Long capabilityId;
    private List<Long> technologiesIds;
}
