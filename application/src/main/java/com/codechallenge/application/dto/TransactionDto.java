package com.codechallenge.application.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_EMPTY)
public class TransactionDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private String reference;
	@NotNull(message = "The account iban can not be null")
	@NotEmpty(message = "The account iban can not be empty")
	private String accountIban;
	private LocalDateTime date;
	@NotNull(message = "The amount can not be null")
	private Double amount;
	private Double fee;
	private String description;
}
