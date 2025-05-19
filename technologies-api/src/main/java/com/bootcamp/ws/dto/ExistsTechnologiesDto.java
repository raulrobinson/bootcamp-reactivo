package com.bootcamp.ws.dto;

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
@Schema(description = "Request DTO for technologies by IDs", title = "Technologies by IDs Request DTO")
public class ExistsTechnologiesDto {
    @Schema(description = "List of technology IDs", example = "[1, 2, 3]")
    private List<Long> technologiesIds;
}
