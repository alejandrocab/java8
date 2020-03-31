package com.smartup.manageorderapplication.utils.mappers;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.smartup.manageorderapplication.dto.ApiErrorDto;
import com.smartup.manageorderapplication.exceptions.ClientNotFoundException;
import com.smartup.manageorderapplication.exceptions.OrderIdsInputInvalidException;
import com.smartup.manageorderapplication.exceptions.OrderUnavailableException;
import com.smartup.manageorderapplication.exceptions.ProductNotFoundException;
import com.smartup.manageorderapplication.exceptions.ShippingCityUnavailableException;

@Component
public class ApiErrorDtoMapper {
	
	private MessageSource messageSource;
	
	public ApiErrorDtoMapper(MessageSource messageSource) {
		this.messageSource=messageSource;
	}
	
	public ApiErrorDto runtimeException2Dto(RuntimeException ex){
		return ApiErrorDto.builder()
				.errorCode("000")
				.message(ex.getMessage())
				.build();
	}
	
	public ApiErrorDto constraintViolationException2Dto(MethodArgumentNotValidException ex, HttpServletRequest request){
		return ApiErrorDto.builder()
		.errorCode("999")
		.message(messageSource.getMessage("validation.errors", null, request.getLocale()))
		.validationErrors(ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()))
		.build();
	}
	
	public ApiErrorDto shippingCityUnavailableException2Dto(ShippingCityUnavailableException ex, HttpServletRequest request){
		String errorMessage = messageSource.getMessage(ShippingCityUnavailableException.ERROR_CODE, new String[] {ex.getCityName()}, request.getLocale());
		return ApiErrorDto.builder()
				.errorCode(ShippingCityUnavailableException.ERROR_CODE)
				.message(errorMessage)
				.build();
	}
	
	public ApiErrorDto clientNotFoundException2Dto(ClientNotFoundException ex, HttpServletRequest request){
		String errorMessage = messageSource.getMessage(ClientNotFoundException.ERROR_CODE, new Long[] {ex.getIdClient()}, request.getLocale());
		return ApiErrorDto.builder()
				.errorCode(ClientNotFoundException.ERROR_CODE)
				.message(errorMessage)
				.build();
	}
	
	public ApiErrorDto productNotFoundException2Dto(ProductNotFoundException ex, HttpServletRequest request){
		String errorMessage = messageSource.getMessage(ProductNotFoundException.ERROR_CODE, new Long[] {ex.getIdProduct()}, request.getLocale());
		return ApiErrorDto.builder()
				.errorCode(ProductNotFoundException.ERROR_CODE)
				.message(errorMessage)
				.build();
	}
	
	public ApiErrorDto orderUnavailableException2Dto(OrderUnavailableException ex, HttpServletRequest request){
		String errorMessage = messageSource.getMessage(OrderUnavailableException.ERROR_CODE, new Long[] {ex.getIdClient()}, request.getLocale());
		return ApiErrorDto.builder()
				.errorCode(OrderUnavailableException.ERROR_CODE)
				.message(errorMessage)
				.build();
	}
	
	public ApiErrorDto orderIdsInputInvalidException2Dto(OrderIdsInputInvalidException ex, HttpServletRequest request){
		String errorMessage = messageSource.getMessage(OrderIdsInputInvalidException.ERROR_CODE, null, request.getLocale());
		return ApiErrorDto.builder()
				.errorCode(OrderIdsInputInvalidException.ERROR_CODE)
				.message(errorMessage)
				.build();
	}
}
