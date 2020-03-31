package com.codechallenge.application.controllers;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.codechallenge.application.dto.ErrorDto;
import com.codechallenge.application.exceptions.AccountNotFoundException;
import com.codechallenge.application.exceptions.AmountAccountNegativeException;
import com.codechallenge.application.exceptions.ChannelUnaviableException;

@RestControllerAdvice
public class HandlerExceptionAdviceController {

	@ExceptionHandler(value=RuntimeException.class)
	protected ResponseEntity<ErrorDto> runtimeExceptionHandler (RuntimeException ex){
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ErrorDto.builder().errorMessage(ex.getMessage()).build());
	}
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorDto> constraintViolationExceptionHandler (MethodArgumentNotValidException ex, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ErrorDto.builder()
						.errorMessage("Input validation errors")
						.validationErrors(ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
						.build());
	}
	
	@ExceptionHandler(value= {AccountNotFoundException.class, AmountAccountNegativeException.class, ChannelUnaviableException.class})
	protected ResponseEntity<ErrorDto> customExceptionHandler (RuntimeException ex){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(ErrorDto.builder().errorMessage(ex.getMessage()).build());
	}
}
