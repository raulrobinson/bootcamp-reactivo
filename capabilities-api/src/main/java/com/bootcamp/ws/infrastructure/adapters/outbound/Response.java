package com.bootcamp.ws.infrastructure.adapters.outbound;

import java.util.List;
import lombok.Data;

@Data
public class Response{
	private List<TechnologiesItem> technologies;
	private int capability;
}