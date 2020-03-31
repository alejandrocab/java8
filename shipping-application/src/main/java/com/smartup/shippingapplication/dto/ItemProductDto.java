package com.smartup.shippingapplication.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(value = Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemProductDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = "{shipping.itemProduct.idProduct.notnull}")
	private Long idProduct;
	@NotNull(message = "{shipping.itemProduct.amount.notnull}")
	@Min(value = 1, message = "{shipping.itemProduct.amount.min}")
	private Long amount;
}
