package com.smartup.manageorderapplication.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.smartup.manageorderapplication.dto.ClientDto;
import com.smartup.manageorderapplication.dto.ItemOrderDto;
import com.smartup.manageorderapplication.dto.OrderDto;
import com.smartup.manageorderapplication.dto.ProductDto;

@SpringBootTest
@ActiveProfiles(profiles = "dev")
public class OrderValidationTest {

	private static Validator validator;
	private static ValidatorFactory validatorFactory;
	
	@BeforeAll
	public static void setUp() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@AfterAll
	public static void tearDown() {
		validatorFactory.close();
	}
	
	@Test
	public void shouldDetectedInvalidInDto() {
		OrderDto inDtoInvalid = OrderDto.builder().build();
		Set<ConstraintViolation<OrderDto>>  violations =  validator.validate(inDtoInvalid);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldDetectedInvalidAddress() {
		ItemOrderDto item = ItemOrderDto.builder()
				.amount(1L)
				.product(ProductDto.builder().build())
				.build();
		OrderDto invalidInDto = OrderDto.builder()
			.address(null)
			.postalCode(28980)
			.shippingCity("shippingCity")
			.client(ClientDto.builder().build())
			.items(Arrays.asList(item))
			.build();
		Set<ConstraintViolation<OrderDto>>  violations =  validator.validate(invalidInDto);
		ConstraintViolation<OrderDto> violationAddress = violations.iterator().next();
		assertEquals(violationAddress.getPropertyPath().toString(), "address");
	}
	
	@Test
	public void shouldDetectedInvalidPostalCode() {
		ItemOrderDto item = ItemOrderDto.builder()
				.amount(1L)
				.product(ProductDto.builder().build())
				.build();
		OrderDto invalidInDto = OrderDto.builder()
			.address("address")
			.postalCode(null)
			.shippingCity("shippingCity")
			.client(ClientDto.builder().build())
			.items(Arrays.asList(item))
			.build();
		Set<ConstraintViolation<OrderDto>>  violations =  validator.validate(invalidInDto);
		ConstraintViolation<OrderDto> violationPostalCode = violations.iterator().next();
		assertEquals(violationPostalCode.getPropertyPath().toString(), "postalCode");
	}
	
	@Test
	public void shouldDetectedInvalidShippingCity() {
		ItemOrderDto item = ItemOrderDto.builder()
				.amount(1L)
				.product(ProductDto.builder().build())
				.build();
		OrderDto invalidInDto = OrderDto.builder()
			.address("address")
			.postalCode(28980)
			.shippingCity(null)
			.client(ClientDto.builder().build())
			.items(Arrays.asList(item))
			.build();
		Set<ConstraintViolation<OrderDto>>  violations =  validator.validate(invalidInDto);
		ConstraintViolation<OrderDto> violationShippingCity = violations.iterator().next();
		assertEquals(violationShippingCity.getPropertyPath().toString(), "shippingCity");
	}
	
	@Test
	public void shouldDetectedInvalidClient() {
		ItemOrderDto item = ItemOrderDto.builder()
				.amount(1L)
				.product(ProductDto.builder().build())
				.build();
		OrderDto invalidInDto = OrderDto.builder()
			.address("address")
			.postalCode(28980)
			.shippingCity("shippingCity")
			.client(null)
			.items(Arrays.asList(item))
			.build();
		Set<ConstraintViolation<OrderDto>>  violations =  validator.validate(invalidInDto);
		ConstraintViolation<OrderDto> violationClient = violations.iterator().next();
		assertEquals(violationClient.getPropertyPath().toString(), "client");
	}
	
	@Test
	public void shouldDetectedInvalidNullItems() {
		OrderDto invalidInDto = OrderDto.builder()
			.address("address")
			.postalCode(28980)
			.shippingCity("shippingCity")
			.client(ClientDto.builder().build())
			.items(null)
			.build();
		Set<ConstraintViolation<OrderDto>>  violations =  validator.validate(invalidInDto);
		ConstraintViolation<OrderDto> violationItems = violations.iterator().next();
		assertEquals("{order.items.notnull}", violationItems.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidEmptyItems() {
		OrderDto invalidInDto = OrderDto.builder()
			.address("address")
			.postalCode(28980)
			.shippingCity("shippingCity")
			.client(ClientDto.builder().build())
			.items(Arrays.asList())
			.build();
		Set<ConstraintViolation<OrderDto>>  violations =  validator.validate(invalidInDto);
		ConstraintViolation<OrderDto> violationItems = violations.iterator().next();
		assertEquals("{order.items.size}", violationItems.getMessage());
	}
}
