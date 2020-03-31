package com.smartup.manageorderapplication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.smartup.manageorderapplication.dto.ApiErrorDto;
import com.smartup.manageorderapplication.exceptions.ClientNotFoundException;
import com.smartup.manageorderapplication.exceptions.OrderIdsInputInvalidException;
import com.smartup.manageorderapplication.exceptions.OrderUnavailableException;
import com.smartup.manageorderapplication.exceptions.ProductNotFoundException;
import com.smartup.manageorderapplication.exceptions.ShippingCityUnavailableException;
import com.smartup.manageorderapplication.utils.mappers.ApiErrorDtoMapper;

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
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(errorMapper.constraintViolationException2Dto(ex, request));
	}
	
	@ExceptionHandler(value=ShippingCityUnavailableException.class)
	protected ResponseEntity<ApiErrorDto> shippingCityUnavailableExceptionHandler (ShippingCityUnavailableException ex, HttpServletRequest request){
		return ResponseEntity.badRequest().body(errorMapper.shippingCityUnavailableException2Dto(ex, request));
	}
	
	@ExceptionHandler(value=ClientNotFoundException.class)
	protected ResponseEntity<ApiErrorDto> clientNotFoundExceptionHandler (ClientNotFoundException ex, HttpServletRequest request){
		return ResponseEntity.badRequest().body(errorMapper.clientNotFoundException2Dto(ex, request));
	}
	
	@ExceptionHandler(value=ProductNotFoundException.class)
	protected ResponseEntity<ApiErrorDto> productNotFoundExceptionHandler (ProductNotFoundException ex, HttpServletRequest request){
		return ResponseEntity.badRequest().body(errorMapper.productNotFoundException2Dto(ex, request));
	}
	
	@ExceptionHandler(value=OrderUnavailableException.class)
	protected ResponseEntity<ApiErrorDto> orderUnavailableExceptionHandler (OrderUnavailableException ex, HttpServletRequest request){
		return ResponseEntity.badRequest().body(errorMapper.orderUnavailableException2Dto(ex, request));
	}
	
	@ExceptionHandler(value=OrderIdsInputInvalidException.class)
	protected ResponseEntity<ApiErrorDto> orderIdsInputInvalidExceptionHandler (OrderIdsInputInvalidException ex, HttpServletRequest request){
		return ResponseEntity.badRequest().body(errorMapper.orderIdsInputInvalidException2Dto(ex, request));
	}
}
