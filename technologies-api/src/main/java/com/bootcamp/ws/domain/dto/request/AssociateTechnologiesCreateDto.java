package com.bootcamp.ws.domain.dto.request;

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
@Schema(description = "Request DTO for Associate Technologies", title = "Request Associate Technologies DTO")
public class AssociateTechnologiesCreateDto {
    @Schema(description = "Technology ID", example = "1")
    private Long capabilityId;

    @Schema(description = "List of Technology IDs", example = "[1, 2, 3]")
    private List<Long> technologiesIds;
}
