package com.codechallenge.application.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.codechallenge.application.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = Include.NON_EMPTY)
public class TransactionStatusDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//input attribute
	private String channel;
	@NotNull(message = "The reference can not be null")
	@NotEmpty(message = "The reference can not be empty")
	private String reference;
	//output attributes
	private StatusEnum status;
	private Double amount;
	private Double fee;
}
