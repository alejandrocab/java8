package com.smartup.shippingapplication.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = Include.NON_EMPTY)
public class ShippingDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	@NotNull(message = "{shipping.idClient.notnull}")
	private Long idClient;
	@NotNull(message = "{shipping.address.notnull}")
	@NotEmpty(message = "{shipping.address.notempty}")
	private String address;
	@NotNull(message = "{shipping.postalCode.notnull}")
	private Integer postalCode;
	@NotNull(message = "{shipping.city.notnull}")
	@NotEmpty(message = "{shipping.city.notempty}")
	private String city;
	@NotNull(message = "{shipping.itemsProduct.notnull}")
	@Size(min = 1, message = "{shipping.itemsProduct.size}")
	@Valid
	private List<ItemProductDto> itemsProduct;
}
