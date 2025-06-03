package com.bootcamp.ws.infrastructure.inbound.mapper;

import com.bootcamp.ws.domain.model.Person;
import com.bootcamp.ws.infrastructure.inbound.dto.PersonDTO;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public Person toDomain(PersonDTO dto) {
        if (dto == null) return null;
        return Person.builder()
                .name(dto.getName())
                .email(dto.getEmail().toLowerCase())
                .age(dto.getAge())
                .build();
    }
}
