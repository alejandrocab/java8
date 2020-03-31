package com.smartup.shippingapplication.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.smartup.shippingapplication.dto.ItemProductDto;
import com.smartup.shippingapplication.dto.ListShippingDto;
import com.smartup.shippingapplication.dto.ShippingDto;


@RunWith(MockitoJUnitRunner.class)
public class ShippingValidationTest {

	private static Validator validator;
	private static ValidatorFactory validatorFactory;
	private ListShippingDto inDto;
	private ShippingDto shipping;
	private ItemProductDto itemProduct;
	
	@BeforeClass
	public static void setUp() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}
	
	@Before
	public void beforeTest() {
		itemProduct = ItemProductDto.builder()
				.amount(1L)
				.idProduct(1L)
				.build();
		shipping = ShippingDto.builder()
				.address("address")
				.city("city")
				.postalCode(00000)
				.idClient(1L)
				.itemsProduct(Arrays.asList(itemProduct))
				.build();
		inDto = ListShippingDto.builder()
				.shippings(Arrays.asList(shipping))
				.build();
	}
	
	@After
	public void afterTest() {
		inDto = null;
		shipping=null;
		itemProduct=null;
	}
	
	
	@AfterClass
	public static void tearDown() {
		validatorFactory.close();
	}
	
	@Test
	public void shouldDetectedInvalidNullListShipping() {
		inDto.setShippings(null);
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violationShippings = violations.iterator().next();
		assertEquals("{shippings.notnull}", violationShippings.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidEmptyListShipping() {
		inDto.setShippings(Arrays.asList());
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violationShippings = violations.iterator().next();
		assertEquals("{shippings.size}", violationShippings.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingAddress() {
		inDto.getShippings().stream().forEach(shipping -> shipping.setAddress(""));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.address.notempty}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingCity() {
		inDto.getShippings().stream().forEach(shipping -> shipping.setCity(""));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.city.notempty}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingPostalCode() {
		inDto.getShippings().stream().forEach(shipping -> shipping.setPostalCode(null));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.postalCode.notnull}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingIdClient() {
		inDto.getShippings().stream().forEach(shipping -> shipping.setIdClient(null));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.idClient.notnull}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingEmptyItemProduct() {
		inDto.getShippings().stream().forEach(shipping -> shipping.setItemsProduct(Arrays.asList()));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.itemsProduct.size}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingNullItemProduct() {
		inDto.getShippings().stream().forEach(shipping -> shipping.setItemsProduct(null));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.itemsProduct.notnull}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingIdProduct() {
		inDto.getShippings().stream()
			.flatMap(shipping->shipping.getItemsProduct().stream())
			.forEach(item-> item.setIdProduct(null));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.itemProduct.idProduct.notnull}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingNullAmountProduct() {
		inDto.getShippings().stream()
			.flatMap(shipping->shipping.getItemsProduct().stream())
			.forEach(item-> item.setAmount(null));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.itemProduct.amount.notnull}", violation.getMessage());
	}
	
	@Test
	public void shouldDetectedInvalidShippingMinAmountProduct() {
		inDto.getShippings().stream()
			.flatMap(shipping->shipping.getItemsProduct().stream())
			.forEach(item-> item.setAmount(0L));
		Set<ConstraintViolation<ListShippingDto>>  violations =  validator.validate(inDto);
		ConstraintViolation<ListShippingDto> violation = violations.iterator().next();
		assertEquals("{shipping.itemProduct.amount.min}", violation.getMessage());
	}
}
