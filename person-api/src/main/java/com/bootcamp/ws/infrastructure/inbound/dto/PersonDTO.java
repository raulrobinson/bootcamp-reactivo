package com.bootcamp.ws.infrastructure.inbound.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDTO {
    private String name;
    private String email;
    private int age;
}
