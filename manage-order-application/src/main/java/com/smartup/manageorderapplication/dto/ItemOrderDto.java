package com.smartup.manageorderapplication.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_EMPTY)
public class ItemOrderDto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotNull(message = "{order.items.amount.notnull}")
	@Min(message = "{order.items.amount.min}", value = 1)
	private Long amount;
	@NotNull(message = "{order.items.product.notnull}")
	private ProductDto product;
}
