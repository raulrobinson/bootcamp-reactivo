package com.bootcamp.ws.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person {
    private Long id;
    private String name;
    private String email;
    private int age;
}
