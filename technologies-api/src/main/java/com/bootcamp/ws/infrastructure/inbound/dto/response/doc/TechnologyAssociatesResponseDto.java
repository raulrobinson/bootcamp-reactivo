package com.bootcamp.ws.infrastructure.inbound.dto.response.doc;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Response DTO for Technology associates", title = "Response Technology associates DTO")
public class TechnologyAssociatesResponseDto {
    @Schema(description = "Technology ID", example = "1")
    private Long id;

    @Schema(description = "Technology Name", example = "Java")
    private String name;
}
