package com.codechallenge.application.mapper;

import static com.codechallenge.application.services.TransactionService.*;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.codechallenge.application.dto.TransactionStatusDto;
import com.codechallenge.application.entities.Transaction;
import com.codechallenge.application.enums.ChannelEnum;
import com.codechallenge.application.enums.StatusEnum;
import com.codechallenge.application.exceptions.ChannelUnaviableException;
import com.codechallenge.application.util.CalculateAmountComponent;

@Component
public class TransactionStatusMapper {

	private CalculateAmountComponent calculateAmountComponent;
	
	public TransactionStatusMapper(CalculateAmountComponent calculateAmountComponent) {
		this.calculateAmountComponent=calculateAmountComponent;
	}
	
	public TransactionStatusDto mapStatus(Transaction entity, String channel) {
		channel = channel!=null?channel.toUpperCase():CLIENT_CHANNEL;	
		if (!ChannelEnum.contains(channel))
			throw new ChannelUnaviableException();
		
		StatusEnum status = getStatus(entity, channel);
		if (INTERNAL_CHANNEL.equalsIgnoreCase(channel))
			return generateDtoWithAmountAndFee(entity, status);
		else
			return generateDtoAmountSubstractingFee(entity, status);
	}
	
	public TransactionStatusDto mapInvalidStatus(String reference) {
		return TransactionStatusDto.builder()
				.reference(reference)
				.status(StatusEnum.INVALID).build();
	}
	
	private StatusEnum getStatus(Transaction entity, String channel) {
		int compareToday = entity.getDate().toLocalDate().compareTo(LocalDate.now());
		if (compareToday<0)
			return StatusEnum.SETTLED;
		else if (compareToday==0 || (ATM_CHANNEL.equalsIgnoreCase(channel)&&compareToday>0))
			return StatusEnum.PENDING;
		else
			return StatusEnum.FUTURE;
	}
	
	private TransactionStatusDto generateDtoAmountSubstractingFee(Transaction entity, StatusEnum status) {
		return TransactionStatusDto.builder()
				.reference(entity.getReference())
				.amount(calculateAmountComponent.calculateTransactionAmount(entity.getAmount(), entity.getFee()))
				.status(status)
				.build();
	}
	
	private TransactionStatusDto generateDtoWithAmountAndFee(Transaction entity, StatusEnum status) {
		return TransactionStatusDto.builder()
				.reference(entity.getReference())
				.amount(entity.getAmount())
				.fee(entity.getFee())
				.status(status)
				.build();
	}
}
