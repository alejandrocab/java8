package com.smartup.manageorderapplication.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import com.smartup.manageorderapplication.dto.ClientDto;

@RunWith(MockitoJUnitRunner.class)
public class ClientValidationTest {

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
		ClientDto inDtoInvalid = ClientDto.builder().build();
		Set<ConstraintViolation<ClientDto>>  violations =  validator.validate(inDtoInvalid);
		assertFalse(violations.isEmpty());
	}
	
	@Test
	public void shouldDetectedInvalidEmail() {
		ClientDto inDtoInvalidEmail = ClientDto.builder()
				.dni("00000000A")
				.name("name")
				.lastname("lastname")
				.email("email@email")
				.build();
		Set<ConstraintViolation<ClientDto>>  violations =  validator.validate(inDtoInvalidEmail);
		ConstraintViolation<ClientDto> violationEmail = violations.iterator().next();
		assertEquals(violationEmail.getPropertyPath().toString(), "email");
	}
	
	@Test
	public void shouldDetectedInvalidName() {
		ClientDto inDtoEmptyAttributes = ClientDto.builder()
				.dni("00000000A")
				.name(null)
				.lastname("lastname")
				.build();
		Set<ConstraintViolation<ClientDto>>  violations =  validator.validate(inDtoEmptyAttributes);
		ConstraintViolation<ClientDto> violationEmail = violations.iterator().next();
		assertEquals(violationEmail.getPropertyPath().toString(), "name");
	}
	
	@Test
	public void shouldDetectedInvalidLastname() {
		ClientDto inDtoEmptyAttributes = ClientDto.builder()
				.dni("00000000A")
				.name("name")
				.lastname(null)
				.build();
		Set<ConstraintViolation<ClientDto>>  violations =  validator.validate(inDtoEmptyAttributes);
		ConstraintViolation<ClientDto> violationEmail = violations.iterator().next();
		assertEquals(violationEmail.getPropertyPath().toString(), "lastname");
	}
	
	@Test
	public void shouldDetectedInvalidDniPattern() {
		ClientDto inDtoEmptyAttributes = ClientDto.builder()
				.dni("00000000-A")
				.name("name")
				.lastname("lastname")
				.build();
		Set<ConstraintViolation<ClientDto>>  violations =  validator.validate(inDtoEmptyAttributes);
		ConstraintViolation<ClientDto> violationEmail = violations.iterator().next();
		assertEquals(violationEmail.getPropertyPath().toString(), "dni");
	}
	
	@Test
	public void shouldDetectedValidInDtoWithoutEmail() {
		ClientDto inDtoEmpty = ClientDto.builder()
				.dni("00000000A")
				.name("name")
				.lastname("lastname")
				.build();
		Set<ConstraintViolation<ClientDto>>  violations =  validator.validate(inDtoEmpty);
		assertTrue(violations.isEmpty());
	}
	
	@Test
	public void shouldDetectedValidInDtoWithEmail() {
		ClientDto inDtoEmpty = ClientDto.builder()
				.dni("00000000A")
				.name("name")
				.lastname("lastname")
				.email("email@email.es")
				.build();
		Set<ConstraintViolation<ClientDto>>  violations =  validator.validate(inDtoEmpty);
		assertTrue(violations.isEmpty());
	}
}
