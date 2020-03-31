package com.codechallenge.application.services;

import java.util.List;

import com.codechallenge.application.dto.TransactionDto;
import com.codechallenge.application.dto.TransactionStatusDto;

public interface TransactionService {
	public static final String CLIENT_CHANNEL="CLIENT";
	public static final String ATM_CHANNEL="ATM";
	public static final String INTERNAL_CHANNEL="INTERNAL";
	public static final String PREFIX_UUID_REFERENCE="REF_";
	public TransactionStatusDto statusTransaction (TransactionStatusDto inDto);
	public TransactionDto createTransaction (TransactionDto inDto);
	public List<TransactionDto> getTransactions (String accountIban, String order);
}
