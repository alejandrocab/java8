package com.codechallenge.application.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(value = Include.NON_EMPTY)
public class ErrorDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String errorMessage;
	private List<String> validationErrors;
}
