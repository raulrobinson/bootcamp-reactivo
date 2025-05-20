package com.bootcamp.ws.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CapabilityWithTechnologiesResponseDto {
    private Long capabilityId;
    private List<Long> technologiesIds;
}
