package com.codechallenge.application.exceptions;

import lombok.Getter;

@Getter
public class ChannelUnaviableException extends RuntimeException{
	private static final long serialVersionUID = -5270353998074401669L;
	private final String message = "The channel is unaviable";
}
