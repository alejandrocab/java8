package com.codechallenge.application.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.codechallenge.application.dto.TransactionDto;

@RunWith(MockitoJUnitRunner.class)
public class TestGetCreateServiceImpl {
	
	@Mock
	private TransactionServicesImpl service;
	

	@Test
	public void whenGetTransactions_ThenReturnNonEmptyList() {
		TransactionDto dto = TransactionDto.builder().reference("REF01").accountIban("ES982138577898300076030").amount(-5.00).fee(0.50).build();
		List<TransactionDto> listDto =Arrays.asList(dto);
		when(service.getTransactions(null, null)).thenReturn(listDto);
		List<TransactionDto> response = service.getTransactions(null, null);
		assertEquals(listDto.size(), response.size());
	}
	
	@Test
	public void givenNotAccountIbanStored_WhenGetTransactions_ThenReturnEmptyList() {
		List<TransactionDto> listDto = Collections.emptyList();
		when(service.getTransactions("XX", null)).thenReturn(listDto);
		List<TransactionDto> response = service.getTransactions("XX", null);
		assertEquals(listDto.size(), response.size());
	}
}
