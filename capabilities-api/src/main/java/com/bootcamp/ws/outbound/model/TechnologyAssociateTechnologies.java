package com.bootcamp.ws.outbound.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyAssociateTechnologies {
    private Long capabilityId;
    private List<Long> technologiesIds;
}
