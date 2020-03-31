package com.smartup.manageorderapplication.exceptions;

import lombok.Getter;

@Getter
public class ShippingCityUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ERROR_CODE = "003";
	private String cityName;
	
	public ShippingCityUnavailableException(String cityName) {
		this.cityName = cityName;
	}
}
