package com.codechallenge.application.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.codechallenge.application.dto.TransactionDto;
import com.codechallenge.application.exceptions.AccountNotFoundException;
import com.codechallenge.application.exceptions.AmountAccountNegativeException;

@RunWith(MockitoJUnitRunner.class)
public class TestTransactionCreateServiceImpl {
	
	@Mock
	private TransactionServicesImpl service;
	

	@Test
	public void givenATransactionWithReference_WhenCheckAmountAccountPositive_ThenCreated() {
		TransactionDto dto = TransactionDto.builder().reference("REF01").accountIban("ES982138577898300076030").amount(-5.00).fee(0.50).build();
		when(service.createTransaction(dto)).thenReturn(dto);
		TransactionDto response = service.createTransaction(dto);
		assertEquals(dto.getReference(), response.getReference());
	}
	
	@Test
	public void givenATransactionWithAccountIbanNotStored_WhenCheckAccountIban_ThenThrowAccountNotFoundException() {
		TransactionDto dto = TransactionDto.builder().accountIban("XES982138577898300076030").amount(-5.00).amount(-5.00).fee(0.50).build();
		when(service.createTransaction(dto)).thenThrow(new AccountNotFoundException());
		assertThrows(AccountNotFoundException.class, ()-> service.createTransaction(dto));
	}
	
	@Test
	public void givenATransaction_WhenCheckAmountAccountNegative_ThenThrowAmountAccountNegativeException(){
		TransactionDto dto = TransactionDto.builder().accountIban("ES982138577898300076030").amount(-50000000000000.00).fee(0.50).build();
		when(service.createTransaction(dto)).thenThrow(new AmountAccountNegativeException());
		assertThrows(AmountAccountNegativeException.class, ()-> service.createTransaction(dto));
	}
}
