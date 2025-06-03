package com.bootcamp.ws.infrastructure.inbound.dto.response.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Response DTO for Capability with Technologies", title = "Response Capability with Technologies DTO")
public class CapabilityWithTechnologiesResponseDto {
    @Schema(description = "Capability ID", example = "1")
    private Long capability;

    @Schema(description = "List of Technology Associates", example = "[{\"id\": 1, \"name\": \"java\"}, {\"id\": 2, \"name\": \"python\"}]")
    private List<TechnologyAssociatesResponseDto> technologies;
}
