package com.bootcamp.ws.domain.dto.response;

import com.bootcamp.ws.infrastructure.adapters.outbound.dto.TechnologyDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response DTO for Capability", title = "Response Capability DTO")
public class CapabilityResponseDto {
    @Schema(description = "Capability ID", example = "1")
    private Long id;

    @Schema(description = "Capability Name", example = "Java")
    private String name;

    @Schema(description = "List of Technology IDs", example = "[{ id: 1, \"name\": \"java\" }, { id: 2, \"name\": \"python\" }, { id: 3, \"name\": \"c#\" }]")
    private List<TechnologyDto> technologies;
}
