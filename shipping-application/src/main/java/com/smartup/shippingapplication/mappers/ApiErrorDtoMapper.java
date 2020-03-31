package com.smartup.shippingapplication.mappers;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.smartup.shippingapplication.dto.ApiErrorDto;

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
}
