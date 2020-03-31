package com.smartup.manageorderapplication.exceptions;

import lombok.Getter;

@Getter
public class OrderUnavailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ERROR_CODE="004";
	private Long idClient;
	
	public OrderUnavailableException(Long idClient) {
		this.idClient=idClient;
	}
}
