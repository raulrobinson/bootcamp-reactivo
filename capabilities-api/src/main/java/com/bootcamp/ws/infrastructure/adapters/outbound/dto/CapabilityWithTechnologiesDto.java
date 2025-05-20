package com.bootcamp.ws.infrastructure.adapters.outbound.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CapabilityWithTechnologiesDto {
    private Long capabilityId;
    private List<Long> technologiesIds;
}
