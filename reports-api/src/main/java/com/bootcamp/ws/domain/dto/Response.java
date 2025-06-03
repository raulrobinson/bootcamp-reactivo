package com.bootcamp.ws.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class Response{
	private List<TechnologiesItem> technologies;
	private int capability;
}