package com.codechallenge.application.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.collection.IsArray;
import org.hamcrest.collection.IsArrayWithSize;
import org.hamcrest.core.IsAnything;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TestGetTransactionController {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final String TRANSACTION_ENDPOINT="/transactions";

	@Test
	public void whenGetTransactions_ThenReturnNonEmptyList() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .get(TRANSACTION_ENDPOINT)
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
				  .andExpect(status().isOk())
				  .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty());
	}
	
	@Test
	public void givenNotAccountIbanStored_WhenGetTransactions_ThenReturnEmptyList() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .get(TRANSACTION_ENDPOINT)
			      .param("accountIban", "XX")
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
				  .andExpect(status().isOk())
				  .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
	}
}
