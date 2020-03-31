package com.smartup.shippingapplication.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.smartup.shippingapplication.dto.ApiErrorDto;
import com.smartup.shippingapplication.mappers.ApiErrorDtoMapper;

@RestControllerAdvice
public class ExceptionHandlerController {
	
	private ApiErrorDtoMapper errorMapper;
	public ExceptionHandlerController(ApiErrorDtoMapper errorMapper) {
		this.errorMapper=errorMapper;
	}
	
	@ExceptionHandler(value=RuntimeException.class)
	protected ResponseEntity<ApiErrorDto> runtimeExceptionHandler (RuntimeException ex){
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(errorMapper.runtimeException2Dto(ex));
	}
	
	/**
	 * Este metodo se crea para las constraint validation de java
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiErrorDto> constraintViolationExceptionHandler (MethodArgumentNotValidException ex, HttpServletRequest request){
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(errorMapper.constraintViolationException2Dto(ex, request));
	}
}
