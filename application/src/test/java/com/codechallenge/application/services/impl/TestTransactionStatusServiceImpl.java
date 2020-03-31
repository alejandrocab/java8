package com.codechallenge.application.services.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.codechallenge.application.dto.TransactionStatusDto;
import com.codechallenge.application.enums.StatusEnum;

@RunWith(MockitoJUnitRunner.class)
public class TestTransactionStatusServiceImpl {
	
	@Mock
	private TransactionServicesImpl service;
	
	@Test
	public void givenATransactionNotStored_WhenCheckStatusAnyChannel_ThenReturnsInvalidStatus(){
		String invalidReference = "XXXXXX"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(invalidReference).channel("CLIENT").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(invalidReference).status(StatusEnum.INVALID).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.INVALID, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusClientOrATMChannelAndisBeforeToday_ThenReturnsSettledStatusAndAmountSubstractingTheFee() {
		String validReference = "12345A"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(validReference).channel("CLIENT").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(validReference).status(StatusEnum.SETTLED).amount(120.0).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.SETTLED, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
		assertNotNull(response.getAmount());
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusInternalChannelAndisBeforeToday_ThenReturnsSettledStatusAndAmountAndFee() {
		String validReference = "12345A"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(validReference).channel("INTERNAL").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(validReference).status(StatusEnum.SETTLED).amount(120.0).fee(20.0).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.SETTLED, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
		assertNotNull(response.getAmount());
		assertNotNull(response.getFee());
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusClientOrATMChannelAndisEqualToday_ThenReturnsPendingStatusAndAmountSubstractingTheFee() throws Exception {
		String validReference = "12345C"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(validReference).channel("ATM").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(validReference).status(StatusEnum.PENDING).amount(55.0).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.PENDING, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
		assertNotNull(response.getAmount());
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusInternalChannelAndisEqualToday_ThenReturnsPendingStatusAndAmountAndFee() {
		String validReference = "12345D"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(validReference).channel("INTERNAL").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(validReference).status(StatusEnum.PENDING).amount(55.0).fee(2.00).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.PENDING, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
		assertNotNull(response.getAmount());
		assertNotNull(response.getFee());
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusClientChannelAndisGreaterThanToday_ThenReturnsFutureStatusAndAmountSubstractingTheFee() throws Exception {
		String validReference = "12345E"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(validReference).channel("CLIENT").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(validReference).status(StatusEnum.FUTURE).amount(43.0).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.FUTURE, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
		assertNotNull(response.getAmount());
	}
	
	@Test
	public void givenATransactionStored_WhenCheckStatusATMChannelAndisGreaterThanToday_ThenReturnsPendingStatusAndAmountSubstractingTheFee() throws Exception {
		String validReference = "12345F"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(validReference).channel("ATM").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(validReference).status(StatusEnum.PENDING).amount(21.0).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.PENDING, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
		assertNotNull(response.getAmount());
	} 
	
	@Test
	public void givenATransactionStored_WhenCheckStatusInternalChannelAndisGreaterThanToday_ThenReturnsFutureStatusAndAmountAndFee() {
		String validReference = "12345F"; 
		TransactionStatusDto inDto = TransactionStatusDto.builder().reference(validReference).channel("INTERNAL").build();
		TransactionStatusDto outDto = TransactionStatusDto.builder().reference(validReference).status(StatusEnum.FUTURE).amount(23.0).fee(2.00).build();
		when(service.statusTransaction(inDto)).thenReturn(outDto);
		TransactionStatusDto response = service.statusTransaction(inDto);
		assertEquals(StatusEnum.FUTURE, response.getStatus());
		assertEquals(inDto.getReference(), response.getReference());
		assertNotNull(response.getAmount());
		assertNotNull(response.getFee());
	}
	
}
