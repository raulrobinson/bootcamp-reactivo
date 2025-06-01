package com.bootcamp.ws.infrastructure.inbound.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class FindAssociatesTechsByCapIdResponseDto {
	private int capabilityId;
	private List<TechnologiesIdsItem> technologiesIds;
}