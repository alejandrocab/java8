package com.smartup.manageorderapplication.exceptions;

import lombok.Getter;

@Getter
public class ClientNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public static final String ERROR_CODE = "001";
	private Long idClient;
	
	public ClientNotFoundException(Long idClient) {
		this.idClient = idClient;
	}
}
