package com.bootcamp.ws.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CapabilityBootcamp {
    private Long capabilityId;
    private Long bootcampId;
    private String createdAt;
    private String updatedAt;
}

