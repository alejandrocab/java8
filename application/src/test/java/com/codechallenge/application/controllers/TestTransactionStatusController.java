package com.codechallenge.application.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.codechallenge.application.dto.TransactionStatusDto;
import com.codechallenge.application.enums.StatusEnum;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestTransactionStatusController {
	
	@Autowired
	private MockMvc mockMvc;
	
	private static final String TRANSACTION_STATUS_ENDPOINT="/transactions/status";

	@Test
	public void givenATransactionNotStored_WhenCheckStatusAnyChannel_ThenReturnsInvalidStatus() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("XXXXXX").channel("CLIENT").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
				  .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.INVALID.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("XXXXXX")));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusClientOrATMChannelAndisBeforeToday_ThenReturnsSettledStatusAndAmountSubstractingTheFee() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345A").channel("CLIENT").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.SETTLED.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("12345A")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount", isA(Double.class)));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusInternalChannelAndisBeforeToday_ThenReturnsSettledStatusAndAmountAndFee() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345A").channel("INTERNAL").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.SETTLED.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("12345A")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount", isA(Double.class)))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.fee", isA(Double.class)));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusClientOrATMChannelAndisEqualToday_ThenReturnsPendingStatusAndAmountSubstractingTheFee() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345C").channel("ATM").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.PENDING.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("12345C")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount", isA(Double.class)));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusInternalChannelAndisEqualToday_ThenReturnsPendingStatusAndAmountAndFee() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345D").channel("INTERNAL").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.PENDING.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("12345D")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount", isA(Double.class)))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.fee", isA(Double.class)));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusClientChannelAndisGreaterThanToday_ThenReturnsFutureStatusAndAmountSubstractingTheFee() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345E").channel("CLIENT").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.FUTURE.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("12345E")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount", isA(Double.class)));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusATMChannelAndisGreaterThanToday_ThenReturnsPendingStatusAndAmountSubstractingTheFee() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345F").channel("ATM").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.PENDING.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("12345F")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount", isA(Double.class)));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusInternalChannelAndisGreaterThanToday_ThenReturnsFutureStatusAndAmountAndFee() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345F").channel("INTERNAL").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.status", is(StatusEnum.FUTURE.name())))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.reference", is("12345F")))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.amount", isA(Double.class)))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.fee", isA(Double.class)));
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusInvalidChannel_ThenReturnsBadRequest() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
			      .put(TRANSACTION_STATUS_ENDPOINT)
			      .content(asJsonString(TransactionStatusDto.builder().reference("12345F").channel("channel").build()))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
				  .andDo(MockMvcResultHandlers.print())
			      .andExpect(status().isBadRequest());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
