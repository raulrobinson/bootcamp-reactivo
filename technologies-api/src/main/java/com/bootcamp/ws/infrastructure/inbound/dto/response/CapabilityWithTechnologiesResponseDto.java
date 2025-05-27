package com.bootcamp.ws.infrastructure.inbound.dto.response;

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
    // TODO: devolver lista de tecnologias (id, name)
    //private List<Long> technologiesIds;
    private List<TechnologyResponseDto> technologiesIds;
}
