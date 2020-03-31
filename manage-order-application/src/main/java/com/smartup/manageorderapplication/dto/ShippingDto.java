package com.smartup.manageorderapplication.dto;

import java.io.Serializable;
import java.util.List;

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
	private Long idClient;
	private String address;
	private Integer postalCode;
	private String city;
	private List<ItemProductDto> itemsProduct;
}
