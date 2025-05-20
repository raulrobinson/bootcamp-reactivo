package com.bootcamp.ws.domain.dto.response;

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
@Schema(description = "Response DTO for Associate Technologies", title = "Response Associate Technologies DTO")
public class AssociateTechnologiesResponseDto {
    @Schema(description = "Capability ID", example = "1")
    private Long capabilityId;

    @Schema(description = "Technology ID", example = "1")
    private Long technologyId;
}
