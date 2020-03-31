package com.smartup.manageorderapplication.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
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
public class ProductDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotNull(message = "{product.name.notnull}")
	@NotEmpty(message = "{product.name.notempty}")
	private String name;
	@NotNull(message = "{product.brand.notnull}")
	@NotEmpty(message = "{product.brand.notempty}")
	private String brand;
	private String model;
	private Double weight;
	@NotNull(message = "{product.price.notnull}")
	private Double price;
	@NotNull(message = "{product.iva.notnull}")
	private Double iva;
	private Double totalPrice;
	private SizeDto size;
}
