package com.bootcamp.ws.infrastructure.adapters.persistence.mapper;

import com.bootcamp.ws.domain.model.Person;
import com.bootcamp.ws.infrastructure.adapters.persistence.entity.PersonEntity;
import org.springframework.stereotype.Component;

@Component
public class PersonEntityMapper {

    public PersonEntity toEntity(Person domain) {
        if (domain == null) return null;
        return PersonEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail().toLowerCase())
                .age(domain.getAge())
                .build();
    }

    public Person toDomain(PersonEntity entity) {
        if (entity == null) return null;
        return Person.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail().toLowerCase())
                .age(entity.getAge())
                .build();
    }
}
