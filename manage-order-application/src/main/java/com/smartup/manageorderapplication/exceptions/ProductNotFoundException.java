package com.smartup.manageorderapplication.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public static final String ERROR_CODE="002";
	private Long idProduct;
	
	public ProductNotFoundException(Long idProduct) {
		this.idProduct=idProduct;
	}
}
