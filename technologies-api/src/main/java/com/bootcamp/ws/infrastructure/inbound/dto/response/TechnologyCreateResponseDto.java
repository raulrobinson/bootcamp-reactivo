package com.bootcamp.ws.infrastructure.inbound.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response DTO for Technology", title = "Response Technology DTO")
public class TechnologyCreateResponseDto {
    @Schema(description = "Technology ID", example = "1")
    private Long id;

    @Schema(description = "Technology Name", example = "Java")
    private String name;

    @Schema(description = "Technology Description", example = "Java programming language")
    private String description;
}
