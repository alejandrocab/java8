package com.codechallenge.application.exceptions;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 5873300283718741205L;
	private final String message = "Account iban does not exists!";
}
