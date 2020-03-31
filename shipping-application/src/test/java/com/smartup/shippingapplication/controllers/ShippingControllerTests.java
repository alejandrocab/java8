package com.smartup.shippingapplication.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartup.shippingapplication.dto.ItemProductDto;
import com.smartup.shippingapplication.dto.ListShippingDto;
import com.smartup.shippingapplication.dto.ShippingDto;

@SpringBootTest
@AutoConfigureMockMvc
public class ShippingControllerTests {

	@Autowired
	private MockMvc mvc;
	
	private static final String SHIPPING_ENDPOINT="/shippings";
	private static final String ERROR_CODE_VALIDATION="999";
	private static ItemProductDto p1;
	private static ItemProductDto p2;
	
	@BeforeAll
	public static void setUpTests () {
		p1 = ItemProductDto.builder().idProduct(new Long(1)).amount(new Long(1)).build();
		p2 = ItemProductDto.builder().idProduct(new Long(2)).amount(new Long(1)).build();
	}
	
	@Test
	public void whenInputValidThenReturnStatusCreated() throws Exception {
		ShippingDto shippingDto = ShippingDto.builder()
				.idClient(new Long(1))
				.city("Madrid")
				.address("C/ Puerto Rico")
				.postalCode(28900)
				.itemsProduct(Arrays.asList(p1,p2))
				.build();
		List<ShippingDto> shippings = Arrays.asList(shippingDto);
		ListShippingDto inDto = ListShippingDto.builder().shippings(shippings).build();
		mvc.perform(MockMvcRequestBuilders
				.post(SHIPPING_ENDPOINT)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAsString(inDto)))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$.shippings.[0].idClient", is(shippingDto.getIdClient().intValue())))
		.andExpect(MockMvcResultMatchers.jsonPath("$.shippings.[0].itemsProduct.length()", is(shippingDto.getItemsProduct().size())));
	}
	
	
	@Test
	public void whenInputInvalidThenReturnStatusBadRequest() throws Exception {
		ShippingDto invalidShippingDto = ShippingDto.builder()
				.idClient(null)
				.city("Madrid")
				.address("C/ Puerto Rico")
				.postalCode(28900)
				.itemsProduct(Arrays.asList(p1,p2))
				.build();
		List<ShippingDto> shippings = Arrays.asList(invalidShippingDto);
		ListShippingDto invalidInDto = ListShippingDto.builder().shippings(shippings).build();
		mvc.perform(MockMvcRequestBuilders
				.post(SHIPPING_ENDPOINT)
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonAsString(invalidInDto)))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("$.errorCode", is(ERROR_CODE_VALIDATION)));
	}
	
	private static String jsonAsString (final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
}
