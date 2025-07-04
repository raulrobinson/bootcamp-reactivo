package com.bootcamp.ws.domain.model;

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
    private List<Long> technologyIds;
}

