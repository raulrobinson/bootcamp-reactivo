package com.bootcamp.ws.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Technology {
    private Long id;
    private String name;
    private String description;
}
