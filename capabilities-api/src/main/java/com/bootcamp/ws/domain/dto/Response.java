package com.bootcamp.ws.domain.dto;

import java.util.List;
import lombok.Data;

@Data
public class Response{
	private List<TechnologiesItem> technologies;
	private int capability;
}