package com.bootcamp.ws.infrastructure.inbound.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for Capability", title = "Request Capability DTO")
public class CapabilityCreateDto {

    @Schema(description = "Name of the Capability", example = "Basic Java")
    private String name;

    @Schema(description = "Description of the Capability", example = "This is a basic Java capability")
    private String description;

    @NotEmpty
    @Schema(description = "List of Technology IDs associated with the Capability", example = "[1, 2, 3]")
    private List<Long> technologyIds;
}
