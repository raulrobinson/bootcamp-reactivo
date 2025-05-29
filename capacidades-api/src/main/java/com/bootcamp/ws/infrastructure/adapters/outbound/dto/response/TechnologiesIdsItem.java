package com.bootcamp.ws.infrastructure.adapters.outbound.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class TechnologiesIdsItem{
	private String name;
	private int id;
}