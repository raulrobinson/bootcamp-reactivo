package com.bootcamp.ws.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Request DTO for Technology", title = "Request Technology DTO")
public class TechnologyCreateRequestDto {

    @Schema(description = "Name of the technology", example = "Java")
    private String name;

    @Schema(description = "Description of the technology", example = "Java is a high-level, class-based, object-oriented programming language.")
    private String description;
}
