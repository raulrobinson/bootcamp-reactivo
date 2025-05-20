package com.bootcamp.ws.domain.model;

import com.bootcamp.ws.infrastructure.adapters.outbound.dto.TechnologyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Capability {
    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
    //private List<Long> technologyIds;
    private List<TechnologyDto> technologies;
}

