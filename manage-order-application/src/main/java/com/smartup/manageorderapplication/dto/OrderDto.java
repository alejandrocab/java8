package com.smartup.manageorderapplication.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.smartup.manageorderapplication.utils.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_EMPTY)
public class OrderDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;

	@NotNull(message = "{order.address.notnull}")
	@NotEmpty(message = "{order.address.notempty}")
	private String address;
	@NotNull(message = "{order.postalCode.notnull}")
	private Integer postalCode;
	@NotNull(message = "{order.shippingCity.notnull}")
	@NotEmpty(message = "{order.address.notempty}")
	private String shippingCity;
	@NotNull(message = "{order.client.notnull}")
	private ClientDto client;
	@NotNull(message = "{order.items.notnull}")
	@Size(min = 1, message = "{order.items.size}")
	@Valid
	private List<ItemOrderDto> items;
	private Double totalPriceOrder;
	private OrderStatus status;
}
