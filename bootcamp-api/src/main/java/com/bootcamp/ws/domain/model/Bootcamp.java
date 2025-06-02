package com.bootcamp.ws.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bootcamp {
    private Long id;
    private String name;
    private String description;
}
