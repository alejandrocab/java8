package com.codechallenge.application.mapper;

import org.springframework.stereotype.Component;

import com.codechallenge.application.dto.TransactionDto;
import com.codechallenge.application.entities.Account;
import com.codechallenge.application.entities.Transaction;

@Component
public class TransactionMapper {
	
	public TransactionDto entity2Dto (Transaction entity) {
		return TransactionDto.builder()
				.reference(entity.getReference())
				.accountIban(entity.getAccount().getAccountIban())
				.amount(entity.getAmount())
				.fee(entity.getFee())
				.description(entity.getDescription())
				.date(entity.getDate())
				.build();
	}
	
	public Transaction dto2Entity (TransactionDto dto, Account account) {
		return Transaction.builder()
				.reference(dto.getReference())
				.account(account)
				.amount(dto.getAmount())
				.fee(dto.getFee())
				.description(dto.getDescription())
				.date(dto.getDate())
				.build();
	}
}
