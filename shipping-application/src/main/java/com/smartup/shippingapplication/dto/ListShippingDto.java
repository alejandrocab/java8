package com.smartup.shippingapplication.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ListShippingDto {

	@Valid
	@NotNull(message = "{shippings.notnull}")
	@Size(min = 1, message = "{shippings.size}")
	private List<ShippingDto> shippings;
}
