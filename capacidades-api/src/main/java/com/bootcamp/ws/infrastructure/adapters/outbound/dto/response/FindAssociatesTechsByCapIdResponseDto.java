package com.bootcamp.ws.infrastructure.adapters.outbound.dto.response;

import com.bootcamp.ws.domain.model.Technology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FindAssociatesTechsByCapIdResponseDto {
	private Long capabilityId;
	private List<Technology> technologiesIds;
}