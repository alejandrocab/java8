package com.codechallenge.application.exceptions;

import lombok.Getter;

@Getter
public class AmountAccountNegativeException extends RuntimeException{
	private static final long serialVersionUID = 6250956821294236543L;
	private final String message="The transaction can not be leave the amount account bellow 0!";
}
