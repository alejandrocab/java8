package com.smartup.manageorderapplication.exceptions;

import lombok.Getter;

@Getter
public class OrderIdsInputInvalidException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public static final String ERROR_CODE = "005";
}
