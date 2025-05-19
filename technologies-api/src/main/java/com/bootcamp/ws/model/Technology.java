package com.bootcamp.ws.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Technology {
    private Long id;
    private String name;
    private String description;
    private String createdAt;
    private String updatedAt;
}
