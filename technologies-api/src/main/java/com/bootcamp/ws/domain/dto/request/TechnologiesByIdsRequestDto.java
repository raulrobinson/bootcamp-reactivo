package com.bootcamp.ws.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "DTO for Technology IDs Request", title = "Technology IDs Request DTO")
public class TechnologiesByIdsRequestDto {
    @Schema(description = "List of Technology IDs", example = "[1, 2, 3]")
    private List<Long> TechnologiesIds;
}
