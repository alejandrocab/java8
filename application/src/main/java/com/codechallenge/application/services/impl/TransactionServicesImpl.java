package com.codechallenge.application.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codechallenge.application.dto.TransactionDto;
import com.codechallenge.application.dto.TransactionStatusDto;
import com.codechallenge.application.entities.Account;
import com.codechallenge.application.entities.Transaction;
import com.codechallenge.application.exceptions.AccountNotFoundException;
import com.codechallenge.application.mapper.TransactionMapper;
import com.codechallenge.application.mapper.TransactionStatusMapper;
import com.codechallenge.application.repositories.AccountRepository;
import com.codechallenge.application.repositories.TransactionRepository;
import com.codechallenge.application.services.TransactionService;
import com.codechallenge.application.util.CalculateAmountComponent;

@Service
public class TransactionServicesImpl implements TransactionService{

	private TransactionRepository repository;
	private TransactionMapper transactionMapper;
	private AccountRepository accountRepository;
	private CalculateAmountComponent calculateAmountComponent;
	private TransactionStatusMapper statusMapper;
	
	public TransactionServicesImpl(TransactionRepository repository, TransactionMapper transactionMapper, AccountRepository accountRepository, CalculateAmountComponent calculateAmountComponent, TransactionStatusMapper statusMapper) {
		this.repository=repository;
		this.transactionMapper=transactionMapper;
		this.accountRepository=accountRepository;
		this.calculateAmountComponent=calculateAmountComponent;
		this.statusMapper=statusMapper;
	}
	
	@Override
	@Transactional
	public List<TransactionDto> getTransactions(String accountIban, String order) {
		Sort sort = Sort.by("amount").descending();
		if (order!=null && order.equalsIgnoreCase("ASC"))
			sort = sort.ascending();
		
		if (accountIban!=null && !accountIban.trim().isEmpty()) 
			return repository.findByAccountAccountIbanLike("%"+accountIban+"%", sort).stream().map(transactionMapper::entity2Dto).collect(Collectors.toList());
		else
			return repository.findAll(sort).stream().map(transactionMapper::entity2Dto).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public TransactionDto createTransaction(TransactionDto inDto) {
		initDto(inDto);
		Account account = accountRepository.findById(inDto.getAccountIban()).orElseThrow(AccountNotFoundException::new);
		Double transactionAmountCalculated = calculateAmountComponent.calculateTransactionAmount(inDto.getAmount(), inDto.getFee());
		Double accountAmountCalculated = calculateAmountComponent.calculateAccountAmount(transactionAmountCalculated, account.getTotalAmount());
		account.setTotalAmount(accountAmountCalculated);
		Transaction tEntity = repository.save(transactionMapper.dto2Entity(inDto, account));
		return transactionMapper.entity2Dto(tEntity);
	}
	
	@Override
	@Transactional
	public TransactionStatusDto statusTransaction(TransactionStatusDto inDto) {
		return repository.findById(inDto.getReference())
				.map(entity -> statusMapper.mapStatus(entity, inDto.getChannel()))
				.orElseGet(()-> statusMapper.mapInvalidStatus(inDto.getReference()));
	}
	
	private void initDto (TransactionDto inDto) {
		inDto.setReference(inDto.getReference()!=null?inDto.getReference():PREFIX_UUID_REFERENCE.concat(UUID.randomUUID().toString()));
		inDto.setFee(inDto.getFee()!=null?inDto.getFee():new Double(0));
		inDto.setDate(inDto.getDate()!=null?inDto.getDate():LocalDateTime.now());
	}
}
