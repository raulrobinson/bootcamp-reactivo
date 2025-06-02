package com.bootcamp.ws.domain.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExistsTechnologies {
    private List<Long> technologiesIds;
}
