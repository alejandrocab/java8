package com.smartup.manageorderapplication.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.smartup.manageorderapplication.dto.ProductDto;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationTest {

	private static Validator validator;
	private static ValidatorFactory validatorFactory;
	
	@BeforeClass
	public static void setUp() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@AfterClass
	public static void tearDown() {
		validatorFactory.close();
	}
	
	@Test
	public void shouldDetectedInvalidInDto() {
		ProductDto inDtoInvalid = ProductDto.builder().build();
		Set<ConstraintViolation<ProductDto>>  violations =  validator.validate(inDtoInvalid);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldDetectedInvalidBrand() {
		ProductDto invalidInDto = ProductDto.builder()
			.brand(null)
			.model("model")
			.name("name")
			.price(100.0)
			.iva(21.0)
			.build();
		Set<ConstraintViolation<ProductDto>>  violations =  validator.validate(invalidInDto);
		ConstraintViolation<ProductDto> violationBrand = violations.iterator().next();
		assertEquals(violationBrand.getPropertyPath().toString(), "brand");
	}
	
	@Test
	public void shouldDetectedInvalidName() {
		ProductDto invalidInDto = ProductDto.builder()
				.brand("brand")
				.model("model")
				.name(null)
				.price(100.0)
				.iva(21.0)
				.build();
			Set<ConstraintViolation<ProductDto>>  violations =  validator.validate(invalidInDto);
			ConstraintViolation<ProductDto> violationName = violations.iterator().next();
			assertEquals(violationName.getPropertyPath().toString(), "name");
	}
	
	@Test
	public void shouldDetectedInvalidPrice() {
		ProductDto invalidInDto = ProductDto.builder()
				.brand("brand")
				.model("model")
				.name("name")
				.price(null)
				.iva(21.0)
				.build();
			Set<ConstraintViolation<ProductDto>>  violations =  validator.validate(invalidInDto);
			ConstraintViolation<ProductDto> violationPrice = violations.iterator().next();
			assertEquals(violationPrice.getPropertyPath().toString(), "price");
	}
	
	@Test
	public void shouldDetectedInvalidIva() {
		ProductDto invalidInDto = ProductDto.builder()
				.brand("brand")
				.model("model")
				.name("name")
				.price(100.0)
				.iva(null)
				.build();
			Set<ConstraintViolation<ProductDto>>  violations =  validator.validate(invalidInDto);
			ConstraintViolation<ProductDto> violationIva = violations.iterator().next();
			assertEquals(violationIva.getPropertyPath().toString(), "iva");
	}
}
